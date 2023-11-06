package ApprovalTests.Groovy.StarterProject

import com.spun.util.ClassUtils
import com.spun.util.tests.StackTraceReflectionResult
import org.approvaltests.core.Options
import org.approvaltests.namer.StackTraceNamer
import org.lambda.functions.Function1
import spock.lang.Specification

class SpockNamer {

    static def use(Specification specification) {
        new Options().forFile().withNamer(SpockNamer.createNamer(specification))
    }

    static def createNamer(Specification specification) {
        def methodName = specification.getSpecificationContext().getCurrentFeature().getName()
        def clazz = specification.getClass()
        File sourceFile = getSourceDir(clazz)

        def result = new StackTraceReflectionResult(sourceFile, clazz.getSimpleName(), clazz.getCanonicalName(), methodName)
        return new StackTraceNamer(result, "")
    }

    private static File getSourceDir(Class<? extends Specification> clazz) {
        return ClassUtils.getSourceDirectory(clazz, new Function1<String, String>()
        {
            public String call(String fileName) {
                return fileName + ".groovy";
            }
        });
    }

    static createMultiNamer(Specification specification) {
        try {
            specification.getSpecificationContext().getCurrentSpec()
            createNamer(specification)
        } catch (IllegalStateException e) {
            new StackTraceNamer()
        }
    }
}
