package com.test.highchart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartContext
import com.highsoft.highcharts.core.HIChartView
import com.highsoft.highcharts.core.HIFunction
import com.highsoft.highcharts.core.HIFunctionInterface
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val colorStr: MutableList<String> = mutableListOf("#DA70D6", "#FF0000", "#0000FF", "#008000", "#A0522D", "#1E90FF", "#B8860B")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chartView = findViewById<HIChartView>(R.id.chartView)
        chartView.plugins = ArrayList(mutableListOf("boost"))
        val options = HIOptions()

        val chart = HIChart().apply {
            type = "spline"
            zoomType = "x"
            pinchType = "x"
            panning = HIPanning().apply {
                enabled = true
                type = "x"
            }
        }
        val boost = HIBoost().apply {
            useGPUTranslations = true
        }
        val tooltip = HITooltip().apply {
            shared = true
            valueDecimals = 2
            followTouchMove = false
            //TODO
            // It would be better to add HIFunction support to the header and footer of HITooltip
//            headerFormatter = HIFunction(HIFunctionInterface { f: HIChartContext -> abs((f.getProperty("value") as Double * 50 / 1000)).toString() + "s" }, arrayOf("value"))
            headerFormat = "{point.key * 50} * 50 / 1000 is the number of seconds<br />"
        }
        val mainTitle = HITitle().apply {
            text = ""
        }
        val credits = HICredits().apply {
            enabled = false
        }
        val nav = HINavigation().apply {
            buttonOptions = HIButtonOptions().apply {
                enabled = false
            }
        }
        val plotOptions = HIPlotOptions().apply {
            line = HILine().apply {
                animationLimit = 1000
                cropThreshold = 100
            }
        }

        val line1 = HILine().apply {
            name = "Voltage"
            data = randomData(60)
            lineWidth = 0.5
            yAxis = 0
            xAxis = 0
            color = HIColor.initWithHexValue(colorStr[0].drop(1))
            animate(false)
        }
        val line2 = HILine().apply {
            name = "Throttle"
            data = randomData()
            lineWidth = 0.5
            yAxis = 1
            xAxis = 0
            color = HIColor.initWithHexValue(colorStr[1].drop(1))
            animate(false)
        }
        val line3 = HILine().apply {
            name = "RPM"
            data = randomData(8000)
            lineWidth = 0.5
            yAxis = 2
            xAxis = 0
            color = HIColor.initWithHexValue(colorStr[2].drop(1))
            animate(false)
        }
        val line4 = HILine().apply {
            name = "Current"
            data = randomData(300, -100)
            lineWidth = 0.5
            yAxis = 3
            xAxis = 0
            color = HIColor.initWithHexValue(colorStr[3].drop(1))
            animate(false)
        }
        val line5 = HILine().apply {
            name = "ESC Temperature"
            data = randomData(120)
            lineWidth = 0.5
            yAxis = 4
            xAxis = 0
            color = HIColor.initWithHexValue(colorStr[4].drop(1))
            animate(false)
        }
        val line6 = HILine().apply {
            name = "Motor Temperature"
            data = randomData(120)
            lineWidth = 0.5
            yAxis = 5
            xAxis = 0
            color = HIColor.initWithHexValue(colorStr[5].drop(1))
            animate(false)
        }

        val hiyAxis1 = HIYAxis().apply {
//            min = 0
//            max = 60
//            tickInterval = 12
            title = HITitle().apply {
                text = ""
            }
            labels = HILabels().apply {
                style = HICSSObject().apply {
                    color = colorStr[0]
                }
            }
            lineColor = HIColor.initWithHexValue(colorStr[0].drop(1))
        }
        val hiyAxis2 = HIYAxis().apply {
//            min = 0
//            max = 100
//            tickInterval = 20
            title = HITitle().apply {
                text = ""
            }
            labels = HILabels().apply {
                style = HICSSObject().apply {
                    color = colorStr[1]
                }
            }
            lineColor = HIColor.initWithHexValue(colorStr[1].drop(1))
        }
        val hiyAxis3 = HIYAxis().apply {
//            min = 0
//            max = 10000
//            tickInterval = 2000
            title = HITitle().apply {
                text = ""
            }
            labels = HILabels().apply {
                style = HICSSObject().apply {
                    color = colorStr[2]
                }
            }
            lineColor = HIColor.initWithHexValue(colorStr[2].drop(1))
        }
        val hiyAxis4 = HIYAxis().apply {
//            min = -100
//            max = 600
//            tickInterval = 150
            opposite = true
            title = HITitle().apply {
                text = ""
            }
            labels = HILabels().apply {
                style = HICSSObject().apply {
                    color = colorStr[3]
                }
            }
            lineColor = HIColor.initWithHexValue(colorStr[3].drop(1))
        }
        val hiyAxis5 = HIYAxis().apply {
//            min = 0
//            max = 130
//            tickInterval = 26
            opposite = true
            title = HITitle().apply {
                text = ""
            }
            labels = HILabels().apply {
                style = HICSSObject().apply {
                    color = colorStr[4]
                }
            }
            lineColor = HIColor.initWithHexValue(colorStr[4].drop(1))
        }
        val hiyAxis6 = HIYAxis().apply {
//            min = 0
//            max = 130
//            tickInterval = 26
            opposite = true
            title = HITitle().apply {
                text = ""
            }
            labels = HILabels().apply {
                style = HICSSObject().apply {
                    color = colorStr[5]
                }
            }
            lineColor = HIColor.initWithHexValue(colorStr[5].drop(1))
        }
        val hixAxis = HIXAxis().apply {
            labels = HILabels().apply {
                style = HICSSObject().apply {
                    color = "#000"
                }
                formatter = HIFunction(
                        HIFunctionInterface { f: HIChartContext -> abs((f.getProperty("value") as Double * 50 / 1000)).toString() + "s" }, arrayOf("value")
                )
            }
            minRange = 5
        }

        options.chart = chart
        options.boost = boost
        options.title = mainTitle
        options.tooltip = tooltip
        options.credits = credits
        options.navigation = nav
        options.plotOptions = plotOptions
        options.xAxis = arrayListOf(hixAxis)
        options.yAxis = arrayListOf(hiyAxis1, hiyAxis2, hiyAxis3, hiyAxis4, hiyAxis5, hiyAxis6)
        options.series = ArrayList(listOf(line1, line2, line3, line4, line5, line6))
        chartView.options = options
    }

    private fun randomData(max: Int = 100, min: Int = 1): ArrayList<*> {
        val lineData = mutableListOf<Number>()
        for (i in 0 until 50000) {
            lineData.add((min..max).random())
        }
        return ArrayList(lineData)
    }
}