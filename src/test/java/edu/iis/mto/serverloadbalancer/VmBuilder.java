package edu.iis.mto.serverloadbalancer;

public class VmBuilder implements Builder<Vm> {

	private int i;

	public VmBuilder ofSize(int i) {
		this.i = i;
		return this;
	}

	public Vm build() {
		return new Vm();
	}

	public static VmBuilder vm() {
		return new VmBuilder();
	}
}
