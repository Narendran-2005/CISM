# Cybercrime Incident Management System (CIMS)

## Project Overview
The **Cybercrime Incident Management System (CIMS)** is a Java console-based application built using **MVC architecture** and **MySQL database**.  
It is designed to manage cybercrime incidents, track evidence, handle suspects and victims, and provide role-based dashboards for various cybersecurity professionals.  

This system simulates a real-world Security Operations Center (SOC) workflow where different roles collaborate in handling incidents, forensic investigations, and threat analysis.

---

## Features
- Role-based access control (different dashboards per role)
- Incident management (create, update, assign, report generation)
- Evidence management (add, update, view)
- Suspect and victim tracking
- Asset management (IT infrastructure, systems under threat)
- Logging of activities
- Malware analysis and sample tracking
- Pentesting and vulnerability management
- Forensic investigation support
- MySQL database integration for persistent storage

---

## Architecture
- **Language:** Java  
- **Framework:** MVC (Model-View-Controller)  
- **Database:** MySQL  

---

## Roles and Responsibilities
### 1. Incident Responder
- Handles: `Incident`, `Evidence`, `Log`
- Dashboard:
  - View All Incidents
  - Add New Incident
  - Add Evidence to Incident
  - Update Incident Status
  - Search Incidents
  - View My Assigned Incidents
  - Generate Incident Report

### 2. Malware Analyst
- Handles: `MalwareAnalysis`, `MalwareSample`
- Dashboard:
  - Upload Malware Sample
  - View All Malware Samples
  - Perform Static Analysis
  - Perform Dynamic Analysis
  - Link Malware to Incident
  - Generate Malware Report

### 3. Pentester
- Handles: `Vulnerability`, `Asset`
- Dashboard:
  - View All Assets
  - Add Vulnerability
  - View Vulnerabilities by Asset
  - Update Vulnerability Status
  - Generate Pentest Report

### 4. Forensic Analyst
- Handles: `Investigation`, `Evidence`, `Suspect`, `Victim`
- Dashboard:
  - Start New Investigation
  - View All Investigations
  - Add Evidence to Investigation
  - Add Suspect / Victim
  - Generate Forensic Report

### 5. Security Analyst
- Handles: `Log`, `Incident`
- Dashboard:
  - View All Logs
  - Monitor Active Incidents
  - Search Logs by Criteria
  - Generate SOC Report

### 6. Security Engineer
- Handles: `Asset`, `Vulnerability`
- Dashboard:
  - View All Assets
  - Register New Asset
  - Update Asset Details
  - View Vulnerability Reports
  - Generate Security Report

---

## Models (Entities)
- `Incident`
- `Evidence`
- `Log`
- `MalwareAnalysis`
- `MalwareSample`
- `Vulnerability`
- `Asset`
- `Investigation`
- `Suspect`
- `Victim`
- `Address`

---

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/Narendran-2005/CISM.git

2. Import the project into your preferred IDE (IntelliJ / Eclipse / VS Code with Java).

3. Configure the MySQL Database using CISM_Database.sql

4. Run the application:
   ```bash
   javac Main.java
   java Main

## Example Workflows

- Incident Responder creates an incident, adds evidence, and assigns it.
- Malware Analyst uploads malware samples and links findings to incidents.
- Pentester scans assets and reports vulnerabilities.
- Forensic Analyst conducts investigations with suspects and victims.
- Security Analyst monitors logs and incidents.
- Security Engineer manages assets and validates vulnerabilities.

## Future Enhancements

- Web-based frontend (Spring Boot + React)
- REST API integration
- Advanced threat intelligence feeds
- AI-assisted incident classification
- Export reports (PDF, CSV)
