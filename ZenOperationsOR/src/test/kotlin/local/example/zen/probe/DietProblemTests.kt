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
class DietProblemTests {

    @Test
    fun minimizationTest() {
        val vegetable = Variable.make("vegetable").lower(1).weight(4)
        val meat = Variable.make("meat").lower(1).weight(10)
        val fruit = Variable.make("fruit").lower(1).weight(7)
        val model = ExpressionsBasedModel()
        val protein = model.addExpression().lower(20)
        val iron = model.addExpression().lower(30)
        val calcium = model.addExpression().lower(10)
        model.addVariable(vegetable)
        model.addVariable(meat)
        model.addVariable(fruit)
        protein.set(vegetable, 5).set(meat, 15).set(fruit, 4)
        iron.set(vegetable, 6).set(meat, 10).set(fruit, 5)
        calcium.set(vegetable, 5).set(meat, 3).set(fruit, 12)
        val optimisation = model.minimise()
        Assert.assertEquals("", (optimisation as Optimisation.Result).state, Optimisation.State.OPTIMAL)
        Assert.assertEquals(27.0, optimisation.value, 0.0)
        Assert.assertEquals(2.5, optimisation.doubleValue(0), 0.0)
        Assert.assertEquals(1.0, optimisation.doubleValue(1), 0.0)
        Assert.assertEquals(1.0, optimisation.doubleValue(2), 0.0)
    }
}
