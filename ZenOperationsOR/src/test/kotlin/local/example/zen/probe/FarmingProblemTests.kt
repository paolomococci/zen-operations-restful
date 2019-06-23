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
class FarmingProblemTests {

    private val variableLettuce = Variable.make("lettuce")
            .lower(0.0).weight(3000.0).integer(true).relax()
    private val variablePotato = Variable.make("potato")
            .lower(0.0).weight(5000.0).integer(true).relax()

    private val expressionsBasedModel = ExpressionsBasedModel()

    private val expressionHectares = expressionsBasedModel.addExpression().upper(12.0)
    private val expressionLettuceSeeds = expressionsBasedModel.addExpression().upper(70.0)
    private val expressionPotatoSeeds = expressionsBasedModel.addExpression().upper(18.0)
    private val expressionFertilizer = expressionsBasedModel.addExpression().upper(160.0)

    private var optimisation: Optimisation? = null

    @Test
    fun maximizationTest() {
        expressionsBasedModel.addVariable(variableLettuce)
        expressionsBasedModel.addVariable(variablePotato)
        expressionHectares.set(variableLettuce, 1.0).set(variablePotato, 1.0)
        expressionLettuceSeeds.set(variableLettuce, 7.0)
        expressionPotatoSeeds.set(variablePotato, 3.0)
        expressionFertilizer.set(variableLettuce, 10.0).set(variablePotato, 20.0)
        optimisation = expressionsBasedModel.maximise()
        Assert.assertEquals("", (optimisation as Optimisation.Result).state, Optimisation.State.OPTIMAL)
        Assert.assertEquals(44000.0, (optimisation as Optimisation.Result).value, 0.0)
        Assert.assertEquals(8.0, (optimisation as Optimisation.Result).doubleValue(0), 0.0)
        Assert.assertEquals(4.0, (optimisation as Optimisation.Result).doubleValue(1), 0.0)
    }
}
