/*
 * Copyright 2017 copyright eastar Jeong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("FunctionName", "unused", "MemberVisibilityCanBePrivate")

package dev.eastar.log

import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Method
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.experimental.and

/** @author eastar*/
object Log {
    var LOG = true
    var FILE_LOG: File? = null


    private const val PREFIX = "``"
    private const val PREFIX_MULTILINE = "${PREFIX}▼"
    private const val LF = "\n"
    private const val MAX_LOG_LINE_BYTE_SIZE = 3600

    private var LOG_PASS_REGEX = "^android\\..+|^com\\.android\\..+|^java\\..+".toRegex()

    private fun getLocator(info: StackTraceElement): String = "(%s:%d)".format(info.fileName, info.lineNumber)

    private fun getTag(info: StackTraceElement): String = runCatching {
        (info.className.takeLastWhile { it != '.' } + "." + info.methodName).run { replace("\\$".toRegex(), "_") }
    }.getOrDefault(info.methodName)

    private fun getStack(): StackTraceElement {
        return Exception().stackTrace.filterNot {
            it.className == javaClass.name
        }.filterNot {
            it.lineNumber < 0
        }.first()
    }

    private fun getStackMethod(methodNameKey: String): StackTraceElement {
        return Exception().stackTrace.filterNot {
            it.className == javaClass.name
        }.run {
            lastOrNull {
                it.methodName == methodNameKey
            } ?: last()
        }
    }

    private fun getStackCaller(methodNameKey: String): StackTraceElement {
        return Exception().stackTrace.filterNot {
            it.className == javaClass.name
        }.run {
            getOrNull(indexOfLast { it.methodName == methodNameKey } + 1) ?: last()
        }
    }

    private fun safeCut(byteArray: ByteArray, startOffset: Int): Int {
        val byteLength = byteArray.size
        if (byteLength <= startOffset) throw ArrayIndexOutOfBoundsException("!!text_length <= start_byte_index")
        if (byteArray[startOffset] and 0xc0.toByte() == 0x80.toByte()) throw java.lang.UnsupportedOperationException("!!start_byte_index must splited index")

        var position = startOffset + MAX_LOG_LINE_BYTE_SIZE
        if (byteLength <= position) return byteLength - startOffset

        while (startOffset <= position) {
            if (byteArray[position] and 0xc0.toByte() != 0x80.toByte()) break
            position--
        }
        if (position <= startOffset) throw UnsupportedOperationException("!!byte_length too small")
        return position - startOffset
    }

    @JvmStatic
    fun p(vararg args: Any?) {
        if (!LOG) return
        val info = getStack()
        val tag = getTag(info)
        val locator = getLocator(info)
        val msg = _MESSAGE(*args)
        println(tag, locator, msg)
    }

    @JvmStatic
    fun println(tag: String, locator: String, msg: String?) {
        if (!LOG) return
        val sa = ArrayList<String>(100)
        val st = StringTokenizer(msg, LF, false)
        while (st.hasMoreTokens()) {
            val byteText = st.nextToken().toByteArray()
            var offset = 0
            while (offset < byteText.size) {
                val count = safeCut(byteText, offset)
                sa.add(PREFIX + String(byteText, offset, count))
                offset += count
            }
        }
        val dots = "...................................................................................."
        val sb = StringBuilder(dots)
        val lastTag = tag.substring((tag.length + locator.length - dots.length).coerceAtLeast(0))
        sb.replace(0, lastTag.length, lastTag)
        sb.replace(sb.length - locator.length, sb.length, locator)
        val adjTag = sb.toString()
        when (sa.size) {
            0 -> println(adjTag + PREFIX)
            1 -> println(adjTag + sa[0])
            else -> println(adjTag + PREFIX_MULTILINE).run { repeat(sa.size) { println(adjTag + it) } }
        }
    }

    @JvmStatic
    fun e(vararg args: Any?) {
        if (!LOG) return
        p(*args)
    }


    @JvmStatic
    fun printStackTrace(e: Throwable) {
        if (!LOG) return
        e.printStackTrace()
    }

    /** dump */
    private fun _MESSAGE(vararg args: Any?): String {
        if (args.isNullOrEmpty())
            return "null[]"

        return args.joinToString {
            runCatching {
                when (it) {
                    //@formatter:off
                    null -> "null"
                    is Class<*> -> _DUMP(it)
                    is Throwable -> _DUMP(it)
                    is Method -> _DUMP(it)
                    is ByteArray -> it.joinToString("") { "%02X".format(it) }
                    else -> it.toString()
                    //@formatter:on
                }
            }.getOrDefault("")
        }
    }

    private fun _DUMP(method: Method): String = method.run {
        "$modifiers ${returnType.simpleName.padEnd(20)
            .take(20)}${declaringClass.simpleName}.$name(${parameterTypes.joinToString { it.simpleName }})"
    }


    private fun _DUMP(cls: Class<*>?): String {
        return cls?.simpleName ?: "null_Class<?>"
    }

    private fun _DUMP(th: Throwable?): String {
        th ?: return "Throwable"
        return if (th.cause == null)
            th.javaClass.simpleName + "," + th.message
        else
            _DUMP(th.cause)
    }

    @JvmStatic
    fun _toHexString(byteArray: ByteArray?): String = byteArray?.joinToString("") { "%02x".format(it) } ?: "!!byte[]"

    @JvmStatic
    fun _toByteArray(hexString: String): ByteArray = hexString.zipWithNext { a, b -> "$a$b" }
        .filterIndexed { index, _ -> index % 2 == 0 }
        .map { it.toInt(16).toByte() }
        .toByteArray()

    @JvmStatic
    fun _DUMP_object(o: Any?): String {
        return _DUMP_object("", o, HashSet())
    }

    private fun _DUMP_object(name: String, value: Any?, duplication: MutableSet<Any>): String {
        val sb = StringBuilder()
        try {
            if (value == null)
                return "null"

            if (value.javaClass.isArray) {
                sb.append(name).append('<').append(value.javaClass.simpleName).append('>').append(" = ")
                //@formatter:off
                val componentType = value.javaClass.componentType
                when {
                    Boolean::class.javaPrimitiveType!!.isAssignableFrom(componentType!!) -> sb.append(
                        Arrays.toString(
                            value as BooleanArray?
                        )
                    )
                    Byte::class.javaPrimitiveType!!.isAssignableFrom(componentType) -> sb.append(
                        if ((value as ByteArray).size < MAX_LOG_LINE_BYTE_SIZE) String(
                            (value as ByteArray?)!!
                        ) else "[" + value.size + "]"
                    )
                    Char::class.javaPrimitiveType!!.isAssignableFrom(componentType) -> sb.append(String((value as CharArray?)!!))
                    Double::class.javaPrimitiveType!!.isAssignableFrom(componentType) -> sb.append(Arrays.toString(value as DoubleArray?))
                    Float::class.javaPrimitiveType!!.isAssignableFrom(componentType) -> sb.append(Arrays.toString(value as FloatArray?))
                    Int::class.javaPrimitiveType!!.isAssignableFrom(componentType) -> sb.append(Arrays.toString(value as IntArray?))
                    Long::class.javaPrimitiveType!!.isAssignableFrom(componentType) -> sb.append(Arrays.toString(value as LongArray?))
                    Short::class.javaPrimitiveType!!.isAssignableFrom(componentType) -> sb.append(Arrays.toString(value as ShortArray?))
                    else -> sb.append((value as Array<*>).contentToString())
                }
                //@formatter:on
            } else if (value.javaClass.isPrimitive //
//					|| (value.getClass().getMethod("toString").getDeclaringClass() != Object.class)// toString이 정의된경우만
                || value.javaClass.isEnum //
                || value is Number //
                || value is Boolean //
                || value is CharSequence
            ) //
            {
                sb.append(name).append('<').append(value.javaClass.simpleName).append('>').append(" = ")
                sb.append(value.toString())
            } else {
                if (duplication.contains(value)) {
                    sb.append(name).append('<').append(value.javaClass.simpleName).append('>').append(" = ")
                    sb.append("[duplication]\n")
                    return sb.toString()
                }
                duplication.add(value)
                if (value is Collection<*>) {
                    sb.append(name).append('<').append(value.javaClass.simpleName).append('>').append(" = ")
                        .append(":\n")
                    val it = value.iterator()
                    while (it.hasNext()) sb.append(_DUMP_object("  $name[item]", it.next(), duplication))
                } else {
                    val clz: Class<*> = value.javaClass
                    sb.append(name).append('<').append(value.javaClass.simpleName).append('>').append(" = ")
                        .append(":\n")
                    for (f in clz.declaredFields) {
                        f.isAccessible = true
                        sb.append(_DUMP_object("  " + name + "." + f.name, f[value], duplication))
                    }
                }
            }
            sb.append("\n")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sb.toString()
    }

    var SEED_S = 0L

    @JvmStatic
    fun tic(vararg args: Any? = arrayOf("")) {
        if (!LOG) return
        synchronized(this) {
            val e = System.currentTimeMillis()
            val s = SEED_S
            val interval = if (SEED_S == 0L) 0L else e - s
            SEED_S = e
            e(String.format(Locale.getDefault(), "%,15d", interval), _MESSAGE(*args))
        }
    }


    private val timeText: String get() = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.ENGLISH).format(Date())

}