package mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import mantis.model.Issue;
import mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "admin");
        return Arrays.stream(projects)
                .map(p -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("mantis.connecturl")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(app.getProperty("mantis.adminlogin"), app.getProperty("mantis.adminpassword"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(app.getProperty("mantis.adminlogin"), app.getProperty("mantis.adminpassword"), issueData);
        IssueData createdIssueData1 = mc.mc_issue_get(app.getProperty("mantis.adminlogin"), app.getProperty("mantis.adminpassword"), issueId);
        return new Issue()
                .withId(createdIssueData1.getId().intValue())
                .withSummary(createdIssueData1.getSummary())
                .withDescription(createdIssueData1.getDescription())
                .withProject(new Project()
                        .withId(createdIssueData1.getProject().getId().intValue())
                        .withName(createdIssueData1.getProject().getName()));
    }
}
