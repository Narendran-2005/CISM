package view;


import models.User;

public class RoleRouter {

    public static void routeUser(User user) {
        switch (user.getRole()) {
            case ADMIN:
                new AdminView().showAdminMenu(user);
                break;

            case PENTESTER:
                new PentesterView().showPentesterView(user);
                break;

            case SECURITY_ENGINEER:
                new SecurityEngineerView().showEngineerMenu(user);
                break;

            case SECURITY_ANALYST:
                new SecurityAnalystView().showAnalystMenu(user);
                break;

            case INCIDENT_RESPONDER:
                new IncidentResponderView().showIncidentResponderMenu(user);
                break;

            case MALWARE_ANALYST:
                new MalwareAnalystView().showMalwareAnalystMenu(user);
                break;
            
            case DF_EXAMINER:
                new ForensicAnalystView().showForensicAnalystMenu(user);
                break;

            default:
                System.out.println("Unknown role: " + user.getRole());
        }
    }
}
