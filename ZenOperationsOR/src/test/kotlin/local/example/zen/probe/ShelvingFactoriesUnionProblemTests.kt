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
class ShelvingFactoriesUnionProblemTests {

    @Test
    fun maximizationTest() {
        val woodenShelves = Variable
                .make("wooden").lower(0).weight(3000).integer(true).relax()
        val aluminumShelves = Variable
                .make("aluminium").lower(0).weight(5000).integer(true).relax()
        val model = ExpressionsBasedModel()
        val factoryOne = model.addExpression().upper(4)
        val factoryTwo = model.addExpression().upper(12)
        val factoryThree = model.addExpression().upper(18)
        model.addVariable(woodenShelves)
        model.addVariable(aluminumShelves)
        factoryOne.set(woodenShelves, 1).set(aluminumShelves, 0)
        factoryTwo.set(woodenShelves, 0).set(aluminumShelves, 2)
        factoryThree.set(woodenShelves, 3).set(aluminumShelves, 2)
        val optimisation = model.maximise()
        Assert.assertEquals("", optimisation.state, Optimisation.State.OPTIMAL)
        Assert.assertEquals(36000.0, optimisation.value, 0.0)
        Assert.assertEquals(2, java.lang.Double.valueOf(optimisation.doubleValue(0)).toLong())
        Assert.assertEquals(6, java.lang.Double.valueOf(optimisation.doubleValue(1)).toLong())
    }
}
