package com.trmsmy.drools.boolean_expression;

import java.util.List;

public class BoolFact {

	private String a;
	private String b;
	private String c;
	private List<String> l;

	private boolean aMatched;
	private boolean bMatched;
	private boolean cMatched;
	private boolean lMatched;

	public boolean isaMatched() {
		return aMatched;
	}

	public void setaMatched(boolean aMatched) {
		this.aMatched = aMatched;
	}

	public boolean isbMatched() {
		return bMatched;
	}

	public void setbMatched(boolean bMatched) {
		this.bMatched = bMatched;
	}

	public boolean iscMatched() {
		return cMatched;
	}

	public void setcMatched(boolean cMatched) {
		this.cMatched = cMatched;
	}

	public boolean islMatched() {
		return lMatched;
	}

	public void setlMatched(boolean lMatched) {
		this.lMatched = lMatched;
	}

	private boolean matched;

	public boolean isMatched() {
		return matched;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public List<String> getL() {
		return l;
	}

	public void setL(List<String> d) {
		this.l = d;
	}

	@Override
	public String toString() {
		return "BoolFact [a=" + a + ", b=" + b + ", c=" + c + ", l=" + l + ", aMatched=" + aMatched + ", bMatched="
				+ bMatched + ", cMatched=" + cMatched + ", lMatched=" + lMatched + ", matched=" + matched + "]";
	}


}
