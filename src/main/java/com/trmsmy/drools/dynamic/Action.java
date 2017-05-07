package com.trmsmy.drools.dynamic;

public class Action {
	private boolean showBanner;

	public void showBanner(boolean flag) {
		this.showBanner = flag;
	}

	@Override
	public String toString() {
		return "Action [showBanner=" + showBanner + "]";
	}
	
	
}
