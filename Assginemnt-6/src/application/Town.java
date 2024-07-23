package application;

public class Town implements Comparable<Town> {
	
	String name;
	boolean visited;
	
	public Town(String name) {
		this.name = name;
	}

	public Town(Town templateTown) {
		this(templateTown.name);
	}
	
	@Override
	public int compareTo(Town o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.getName());
	}
	
	public boolean equals(Object o) {
		Town t =(Town)o;
		return name.equals(t.getName());
	}

	public String getName() {
		return name;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return name;
	}


}
