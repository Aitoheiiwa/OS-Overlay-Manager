package overlayManager;

public class MutableBoolean {
	private Boolean value;
	public MutableBoolean() {
		this.value = false;
	}
	public boolean get() {
		return this.value;
	}
	public void set(boolean value) {
		this.value = value;
	}
	public void flip() {
		this.value = !this.value;
	}

}
