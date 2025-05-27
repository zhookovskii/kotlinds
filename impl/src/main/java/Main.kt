import com.zhukovskii.kotlinds.estimator.linear.SGDRegression
import com.zhukovskii.kotlinds.penalty.L2Penalty
import com.zhukovskii.kotlinds.score.mse
import org.jetbrains.kotlinx.kandy.dsl.invoke
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.multik.api.*
import org.jetbrains.kotlinx.multik.ndarray.data.*
import org.jetbrains.kotlinx.multik.ndarray.operations.*
import java.awt.BorderLayout
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.WindowConstants

fun targetFunction(x: MultiArray<Double, D1>): Double {
    return (x * 3.0 - 17.0 + mk.rand<Double, D1>(from = -1.0, until = 1.0, x.size)).sum()
}

fun main() {
    val trainSize = 1000
    val testSize = 10
    val features = 10
    val epochs = 100000

    val trainData = mk.rand<Double, D2>(from = -10.0, until = 10.0, trainSize, features)
    val trainTarget = mk.d1array(trainSize) { index -> targetFunction(trainData[index]) }

    val testData = mk.rand<Double, D2>(from = -10.0, until = 10.0, testSize, features)
    val testTarget = mk.d1array(testSize) { index -> targetFunction(testData[index]) }

    val model = SGDRegression(
        epochs = epochs,
        learningRate = 1e-2,
        penalty = L2Penalty(0.5),
        trackLoss = true
    )
    model.fit(trainData, trainTarget)

    val prediction = model.predict(testData)
    val score = mse(testTarget, prediction)
    println("MSE score: $score")

    plot(
        mapOf(
            "x" to mk.linspace<Double>(-10, 10, num = testSize).toList(),
            "target" to testTarget.toList(),
            "predicted" to prediction.toList()
        )
    ) {
        x("x"<Double>())

        points {
            y("target"<Double>())
            color = Color.GREEN
        }
        line {
            y("predicted"<Double>())
            color = Color.RED
        }
    }.save("function.png")

    plot(
        mapOf(
            "epochs" to (1 .. model.trainHistory.size).toList(),
            "loss" to model.trainHistory
        )
    ) {
        x("epochs"<Double>())

        line {
            y("loss"<Double>())
        }
    }.save("loss.png")

    plotImage("function.png")
    plotImage("loss.png")
}

private fun plotImage(path: String) {
    val imageFile = File("lets-plot-images/$path")
    val image = ImageIO.read(imageFile)

    val icon = ImageIcon(image)

    val label = JLabel(icon)
    val frame = JFrame("PNG Viewer")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.contentPane.add(label, BorderLayout.CENTER)
    frame.pack()
    frame.isVisible = true
}