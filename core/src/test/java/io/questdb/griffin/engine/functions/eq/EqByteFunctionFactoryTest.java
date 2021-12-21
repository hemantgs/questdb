/*******************************************************************************
 *     ___                  _   ____  ____
 *    / _ \ _   _  ___  ___| |_|  _ \| __ )
 *   | | | | | | |/ _ \/ __| __| | | |  _ \
 *   | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *    \__\_\\__,_|\___||___/\__|____/|____/
 *
 *  Copyright (c) 2014-2019 Appsicle
 *  Copyright (c) 2019-2022 QuestDB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package io.questdb.griffin.engine.functions.eq;

import io.questdb.cairo.sql.Function;
import io.questdb.griffin.FunctionFactory;
import io.questdb.griffin.SqlException;
import io.questdb.griffin.engine.AbstractFunctionFactoryTest;
import io.questdb.griffin.engine.functions.constants.NullConstant;
import io.questdb.griffin.engine.functions.constants.ShortConstant;
import io.questdb.std.IntList;
import io.questdb.std.ObjList;
import org.junit.Assert;
import org.junit.Test;

public class EqByteFunctionFactoryTest extends AbstractFunctionFactoryTest {

    @Test
    public void testAll() throws SqlException {
        call(0, 0).andAssert(true);
        call(0, 1).andAssert(false);
    }

    @Test
    public void testRightNull() {
        FunctionFactory factory = getFunctionFactory();
        ObjList<Function> args = new ObjList<>();
        args.add(new ShortConstant((short) 1));
        args.add(NullConstant.NULL);

        IntList argPositions = new IntList();
        argPositions.add(1);
        argPositions.add(2);
        Assert.assertThrows("Value for byte column cannot be Null", SqlException.class,
                () -> factory.newInstance(4, args, argPositions, configuration, sqlExecutionContext));
    }

    @Override
    protected FunctionFactory getFunctionFactory() {
        return new EqByteFunctionFactory();
    }
}