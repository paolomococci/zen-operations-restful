/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.zen.probe

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.ojalgo.optimisation.ExpressionsBasedModel
import org.ojalgo.optimisation.Optimisation
import org.ojalgo.optimisation.Variable
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ProductionAndExcessCapacityProblemTests {

    @Test
    fun maximizationTest() {
        val latexFoamLowDensity = Variable
                .make("L").lower(0).upper(600).weight(9).integer(true).relax()
        val latexFoamMidDensity = Variable
                .make("M").lower(0).upper(800).weight(10).integer(true).relax()
        val latexFoamHighDensity = Variable
                .make("H").lower(0).upper(500).weight(12).integer(true).relax()
        val model = ExpressionsBasedModel()
        val workerExcessCapacity = model.addExpression().upper(500)
        val warehouseExcessCapacity = model.addExpression().upper(900)
        model.addVariable(latexFoamLowDensity)
        model.addVariable(latexFoamMidDensity)
        model.addVariable(latexFoamHighDensity)
        workerExcessCapacity
                .set(latexFoamLowDensity, 1).set(latexFoamMidDensity, 1).set(latexFoamHighDensity, 1)
        warehouseExcessCapacity
                .set(latexFoamLowDensity, 2).set(latexFoamMidDensity, 1.5).set(latexFoamHighDensity, 1)
        val optimisation = model.maximise()
        Assert.assertEquals("", (optimisation as Optimisation.Result).state, Optimisation.State.OPTIMAL)
        Assert.assertEquals(6000, java.lang.Double.valueOf(optimisation.value).toLong())
        Assert.assertEquals(0, java.lang.Double.valueOf(optimisation.doubleValue(0)).toLong())
        Assert.assertEquals(0, java.lang.Double.valueOf(optimisation.doubleValue(1)).toLong())
        Assert.assertEquals(500, java.lang.Double.valueOf(optimisation.doubleValue(2)).toLong())
    }
}
