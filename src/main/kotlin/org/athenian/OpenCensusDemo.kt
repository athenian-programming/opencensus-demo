package org.athenian

import io.opencensus.exporter.stats.prometheus.PrometheusStatsCollector
import io.opencensus.stats.Aggregation.*
import io.opencensus.stats.BucketBoundaries
import io.opencensus.stats.Measure.MeasureLong
import io.opencensus.stats.Stats
import io.opencensus.stats.View
import io.opencensus.stats.View.Name
import io.opencensus.tags.TagKey
import io.opencensus.tags.TagValue
import io.opencensus.tags.Tags
import io.prometheus.client.exporter.HTTPServer
import java.lang.Thread.sleep
import java.util.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis


fun main() {

    val DURATION_MS = MeasureLong.create("demo/call_duration", "The call duration in milliseconds", "ms")

    val tagger = Tags.getTagger()
    val KEY_METHOD = TagKey.create("method");

    val statsRecorder = Stats.getStatsRecorder()

    PrometheusStatsCollector.createAndRegister()
    HTTPServer("localhost", 8888, true)

    Stats.getViewManager()
        .apply {
            registerView(
                View.create(
                    Name.create("demo/dist_duration"),
                    "The distribution of the calls",
                    DURATION_MS,
                    Distribution.create(BucketBoundaries.create(listOf(0.0, 250.0, 500.0, 750.0, 1000.0))),
                    Collections.singletonList(KEY_METHOD)
                )
            )
            registerView(
                View.create(
                    Name.create("demo/count_duration"),
                    "The number of times call was made",
                    DURATION_MS,
                    Count.create(),
                    Collections.singletonList(KEY_METHOD)
                )
            )
            registerView(
                View.create(
                    Name.create("demo/sum_duration"),
                    "The sum of all calls",
                    DURATION_MS,
                    Sum.create(),
                    Collections.singletonList(KEY_METHOD)
                )
            )
            registerView(
                View.create(
                    Name.create("demo/last_duration"),
                    "The time required for the last invocation",
                    DURATION_MS,
                    LastValue.create(),
                    Collections.singletonList(KEY_METHOD)
                )
            )
        }

    val tagContext = tagger.emptyBuilder().put(KEY_METHOD, TagValue.create("expensiveCall-method")).build()

    while (true) {
        val timeMs = measureTimeMillis { expensiveCall() }
        println(timeMs)
        tagger.withTagContext(tagContext).use {
            statsRecorder.newMeasureMap().put(DURATION_MS, timeMs).record()
        }
    }
}

fun expensiveCall() {
    sleep(Random.nextLong(0, 1000))
}