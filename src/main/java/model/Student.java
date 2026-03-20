package model;

import jakarta.persistence.*;

@Entity
@Table (name = "student")

public class Student {
	
	@Id
	@Column(name = "reg_no") 
    private int regNo; 
 
    @Column(name = "s_no") 
    private String sNo; 
 
    @Column(name = "name") 
    private String name; 
 
    @Column(name = "email", unique = true, nullable = false) 
    private String email;
    
    @Column(name = "center")
    private String center;
    
    @Column(name = "program")
    private String program;
    
    
    public Student() {
		
	}


	public Student(int regNo, String sNo, String name, String email, String center, String program) {
		super();
		this.regNo = regNo;
		this.sNo = sNo;
		this.name = name;
		this.email = email;
		this.center = center;
		this.program = program;
	}


	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	@Override
	public String toString() {
		
		return "Student [regNo=" + getRegNo() + "sNo=" + getsNo() + "name=" + getName() + "email=" + getEmail() + "center=" + getCenter() + "program=" + getProgram() +"]";
		
	}

}
