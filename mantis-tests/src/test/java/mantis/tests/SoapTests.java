package mantis.tests;

import mantis.model.Issue;
import mantis.model.Project;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;


public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        projects.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testCheckStatus_new() throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = new Issue().withId(0000001);
        try {
            skipIfNotFixed(issue.getId());
            System.out.println("Test 'testCheckStatus_new' completed!");
        } catch (SkipException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCheckStatus_resolve() throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = new Issue().withId(0000002);
        try {
            skipIfNotFixed(issue.getId());
            System.out.println("Test 'testCheckStatus_resolve' completed!");
        } catch (SkipException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testCheckStatus_close() throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = new Issue().withId(0000003);
        try {
            skipIfNotFixed(issue.getId());
            System.out.println("Test 'testCheckStatus_close' completed!");
        } catch (SkipException e) {
            System.out.println(e);
        }
    }


}
