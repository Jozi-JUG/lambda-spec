package pl.project13.test.lambda.specset;

/*
 * #%L
 * lambda-spec
 * %%
 * Copyright (C) 2013 project13.pl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import pl.project13.test.lambda.RegisteredSpec;
import pl.project13.test.lambda.options.Options;
import pl.project13.test.lambda.spec.result.TestResult;
import pl.project13.test.lambda.spec.result.TestResults;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class RunnableLambdaSpec {

  abstract protected List<RegisteredSpec> allTests();
  abstract protected String describe();

  public TestResults shouldPassAllTests() {
    Stream<RegisteredSpec> tests = Options.runParallel() ? allTests().parallelStream() : allTests().stream();

    List<TestResult> results = tests.map(RegisteredSpec::run).collect(toList());
    TestResults testResults = new TestResults(describe(), results);
    testResults.printToConsole();

    return testResults;
  }
}
