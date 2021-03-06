package org.jetbrains.kotlin.benchmarks

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
open class InferenceBaselineCallsBenchmark : AbstractSimpleFileBenchmark(){

    @Param("1", "10", "100", "1000", "5000", "10000")
    private var size: Int = 0

    @Benchmark
    fun benchmark(bh: Blackhole) {
        analyzeGreenFile(bh)
    }

    override fun buildText() =
            """
            |fun foo(x: Int): Int = 1
            |var x = 1
            |fun bar(v: Int) {
            |${(1..size).map { "    x = foo(v)" }.joinToString("\n")}
            |}
            """.trimMargin()
}

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
open class InferenceExplicitArgumentsCallsBenchmark : AbstractSimpleFileBenchmark(){

    @Param("1", "10", "100", "1000", "5000", "10000")
    private var size: Int = 0

    @Benchmark
    fun benchmark(bh: Blackhole) {
        analyzeGreenFile(bh)
    }

    override fun buildText() =
            """
            |fun <T> foo(x: T): Int = 1
            |var x = 1
            |fun bar(v: Int) {
            |${(1..size).map { "    x = foo<Int>(v)" }.joinToString("\n")}
            |}
            """.trimMargin()
}

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
open class InferenceFromArgumentCallsBenchmark : AbstractSimpleFileBenchmark(){

    @Param("1", "10", "100", "1000", "5000", "10000")
    private var size: Int = 0

    @Benchmark
    fun benchmark(bh: Blackhole) {
        analyzeGreenFile(bh)
    }

    override fun buildText() =
            """
            |fun <T> foo(x: T): Int = 1
            |var x = 1
            |fun bar(v: Int) {
            |${(1..size).map { "    x = foo(v)" }.joinToString("\n")}
            |}
            """.trimMargin()
}

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
open class InferenceFromReturnTypeCallsBenchmark : AbstractSimpleFileBenchmark(){

    @Param("1", "10", "100", "1000", "5000", "10000")
    private var size: Int = 0

    @Benchmark
    fun benchmark(bh: Blackhole) {
        analyzeGreenFile(bh)
    }

    override fun buildText() =
            """
            |fun <T> foo(x: Int): T = null!!
            |var x = 1
            |fun bar(v: Int) {
            |${(1..size).map { "    x = foo(v)" }.joinToString("\n")}
            |}
            """.trimMargin()
}
