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
class RemoteControlBuilderProblemTests {

    @Test
    fun maximizationTest() {
        val remoteControlOne = Variable
                .make("one").lower(0).weight(3).integer(true).relax()
        val remoteControlTwo = Variable
                .make("two").lower(0).weight(8).integer(true).relax()
        val model = ExpressionsBasedModel()
        val displayModules = model.addExpression().upper(10)
        val navigationModule = model.addExpression().upper(9)
        val miniKeypadModules = model.addExpression().upper(21)
        val controlLogicModules = model.addExpression().upper(18)
        val transmissionModules = model.addExpression().upper(12)
        val ledModules = model.addExpression().upper(10)
        model.addVariable(remoteControlOne)
        model.addVariable(remoteControlTwo)
        displayModules.set(remoteControlOne, 1).set(remoteControlTwo, 2)
        navigationModule.set(remoteControlOne, 1)
        miniKeypadModules.set(remoteControlOne, 2).set(remoteControlTwo, 3)
        controlLogicModules.set(remoteControlOne, 2).set(remoteControlTwo, 2)
        transmissionModules.set(remoteControlOne, 1).set(remoteControlTwo, 3)
        ledModules.set(remoteControlOne, 1)
        val optimisation = model.maximise()
        Assert.assertEquals("", (optimisation as Optimisation.Result).state, Optimisation.State.OPTIMAL)
        Assert.assertEquals(34, java.lang.Double.valueOf(optimisation.value).toLong())
        Assert.assertEquals(5, java.lang.Double.valueOf(optimisation.doubleValue(0)).toLong())
        Assert.assertEquals(2, java.lang.Double.valueOf(optimisation.doubleValue(1)).toLong())
    }
}
