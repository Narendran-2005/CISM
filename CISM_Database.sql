CREATE DATABASE CISM_DB;
USE CISM_DB;

-- 1. Users
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM(
        'ADMIN', 'SECURITY_ANALYST', 'SECURITY_ENGINEER',
        'INCIDENT_RESPONDER', 'MALWARE_ANALYST', 'PENTESTER', 'DF_EXAMINER'
    ) NOT NULL,
    email VARCHAR(100) UNIQUE
);

-- 2. Address
CREATE TABLE address (
    id INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    postal_code VARCHAR(20)
);

-- 3. Person
CREATE TABLE person (
    person_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    dob DATE,
    gender ENUM('Male', 'Female', 'Other'),
    address_id INT,
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE SET NULL
);

-- 4. Incidents
CREATE TABLE incidents (
    incident_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    incident_date DATETIME NOT NULL,
    reported_by INT,
    status ENUM('Open', 'Under Investigation', 'Resolved', 'Closed') DEFAULT 'Open',
    FOREIGN KEY (reported_by) REFERENCES users(user_id) ON DELETE SET NULL
    FOREIGN KEY (assigned_to) REFERENCES users(user_id) ON DELETE SET NULL
);

-- 5. Victims
CREATE TABLE victims (
    victim_id INT PRIMARY KEY AUTO_INCREMENT,
    person_id INT,
    incident_id INT,
    recieved_support ENUM('Yes','No'),
    FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id) ON DELETE CASCADE
);

-- 6. Suspects
CREATE TABLE suspects (
    suspect_id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT,
    aliases TEXT,
    criminal_records INT,
    interrogation_status VARCHAR(100),
    FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE CASCADE
);

-- 7. Suspect ↔ Cases (associated cases)
CREATE TABLE suspect_cases (
    person_id INT,
    case_reference VARCHAR(100),
    FOREIGN KEY (person_id) REFERENCES suspects(person_id) ON DELETE CASCADE
);

-- 8. Investigations
CREATE TABLE investigations (
    investigation_id INT AUTO_INCREMENT PRIMARY KEY,
    incident_id INT,
    investigator_id INT,
    start_date DATE NOT NULL,
    end_date DATE,
    status ENUM('Ongoing', 'Completed') DEFAULT 'Ongoing',
    findings TEXT,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id) ON DELETE CASCADE,
    FOREIGN KEY (investigator_id) REFERENCES users(user_id) ON DELETE SET NULL
);

-- 9. Threats
CREATE TABLE threats (
    threat_id INT AUTO_INCREMENT PRIMARY KEY,
    threat_name VARCHAR(100) NOT NULL UNIQUE,
    threat_type VARCHAR(100) NOT NULL,
    severity ENUM('Low', 'Medium', 'High', 'Critical') NOT NULL,
    description TEXT,
    incident_id INT,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id) ON DELETE CASCADE
);

-- 10. Assets
CREATE TABLE assets (
    asset_id INT AUTO_INCREMENT PRIMARY KEY,
    incident_id INT,
    threat_id INT,
    asset_name VARCHAR(100) NOT NULL,
    asset_type VARCHAR(100),
    owner VARCHAR(100) NOT NULL,
    status ENUM('Compromised', 'Safe', 'Under Investigation') DEFAULT 'Under Investigation',
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id) ON DELETE CASCADE,
    FOREIGN KEY (threat_id) REFERENCES threats(threat_id) ON DELETE CASCADE
);

-- 11. Evidence
CREATE TABLE evidence (
    evidence_id INT AUTO_INCREMENT PRIMARY KEY,
    incident_id INT,
    evidence_type VARCHAR(100) NOT NULL,
    description TEXT,
    collected_by INT,
    collection_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id) ON DELETE CASCADE,
    FOREIGN KEY (collected_by) REFERENCES users(user_id) ON DELETE SET NULL
);

-- 12. Mitigation
CREATE TABLE mitigation (
    mitigation_id INT AUTO_INCREMENT PRIMARY KEY,
    threat_id INT,
    action_taken TEXT NOT NULL,
    implemented_by INT,
    implementation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    mitigation_status ENUM('Implemented','Yet to implement'),
    effectiveness ENUM('Effective', 'Partially Effective', 'Not Effective') DEFAULT NULL,
    FOREIGN KEY (threat_id) REFERENCES threats(threat_id) ON DELETE CASCADE,
    FOREIGN KEY (implemented_by) REFERENCES users(user_id) ON DELETE SET NULL
);

-- 13. Logs
CREATE TABLE logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    action VARCHAR(255) NOT NULL,
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);

-- 14. Malware Analysis
CREATE TABLE malware_analysis (
    analysis_id INT PRIMARY KEY AUTO_INCREMENT,
    sample_id INT NOT NULL,
    static_analysis TEXT,
    dynamic_analysis TEXT,
    risk_level ENUM('LOW','MEDIUM','HIGH','CRITICAL'),
    analysis_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    analyzed_by INT NOT NULL,
    FOREIGN KEY (sample_id) REFERENCES malware_samples(sample_id),
    FOREIGN KEY (analyzed_by) REFERENCES users(user_id)
);

-- 15. Malware Sample
CREATE TABLE malware_samples (
    sample_id INT PRIMARY KEY AUTO_INCREMENT,
    incident_id INT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_hash VARCHAR(64) NOT NULL, -- SHA256
    file_size BIGINT,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    uploaded_by INT NOT NULL,
    FOREIGN KEY (incident_id) REFERENCES incidents(incident_id),
    FOREIGN KEY (uploaded_by) REFERENCES users(user_id)
);
