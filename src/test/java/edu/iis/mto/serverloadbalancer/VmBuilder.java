package edu.iis.mto.serverloadbalancer;

public class VmBuilder {

	private int i;

	public VmBuilder ofSize(int i) {
		this.i = i;
		return this;
	}

	public Vm build() {
		return new Vm();
	}

}
