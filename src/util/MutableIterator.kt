package dev.schlaubi.aoc2023.util

class MutableIterator<T>(private val list: MutableList<T>) : Iterator<T>{

    private var cursor: Int = 0

    override fun hasNext(): Boolean {
        return cursor != list.size
    }

    override fun next(): T {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        return list.elementAt(cursor++)
    }

    fun add(element: T) = list.add(cursor, element)
}

fun <T> MutableList<T>.mutableListIterator() = MutableIterator(this)
