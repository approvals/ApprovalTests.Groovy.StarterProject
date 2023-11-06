package ApprovalTests.Groovy.StarterProject

import org.approvaltests.Approvals
import spock.lang.Specification

class ApprovalSpecification extends Specification{

    def setup() {
        Approvals.namerCreater = { SpockNamer.createMultiNamer(this) }
    }
}
