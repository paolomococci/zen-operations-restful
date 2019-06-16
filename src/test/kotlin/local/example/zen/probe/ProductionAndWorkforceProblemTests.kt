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
class ProductionAndWorkforceProblemTests {

    @Test
    fun maximizationTest() {
        val one = Variable.make("one")
                .lower(200).weight(30).integer(true).relax()
        val two = Variable.make("two")
                .lower(200).weight(20).integer(true).relax()
        val three = Variable.make("three")
                .lower(150).weight(50).integer(true).relax()
        val model = ExpressionsBasedModel()
        val supplierOne = model.addExpression().upper(40000)
        val supplierTwo = model.addExpression().upper(6000)
        val workPower = model.addExpression().upper(700)
        model.addVariable(one)
        model.addVariable(two)
        model.addVariable(three)
        supplierOne.set(one, 2).set(two, 3).set(three, 5)
        supplierTwo.set(one, 4).set(two, 2).set(three, 7)
        workPower.set(one, 1).set(two, 0.5).set(three, 0.333)
        val optimisation = model.maximise()
        Assert.assertEquals("", (optimisation as Optimisation.Result).state, Optimisation.State.OPTIMAL)
        Assert.assertEquals(46708, java.lang.Double.valueOf(optimisation.value).toLong())
        Assert.assertEquals(200, java.lang.Double.valueOf(optimisation.doubleValue(0)).toLong())
        Assert.assertEquals(623, java.lang.Double.valueOf(optimisation.doubleValue(1)).toLong())
        Assert.assertEquals(564, java.lang.Double.valueOf(optimisation.doubleValue(2)).toLong())
    }
}
