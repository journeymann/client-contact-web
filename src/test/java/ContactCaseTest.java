import com.hlpp.clientcontact.domain.ContactCase;
import com.hlpp.clientcontact.domain.Policy;
import com.hlpp.clientcontact.dao.PolicyDao;
import com.hlpp.clientcontact.dao.PolicyDaoImpl;

public class ContactCaseTest {

    private ContactCase contact;
    private Policy policy;

    private PolicyDao dao = new PolicyDaoImpl();

    public void setUp() {

		//policy = dao.findPolicyById(1);

    }

    public void findResultsTest() throws Exception {
		//Set<ContactCase> contacts = policy.getContactCases();

		//System.out.println("Set size = "+contacts.size());
        System.out.println("End of Results");
    }

}