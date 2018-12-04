class RideIterator {
	
	private Ride[] rides;
	private int currElement;
	private int numElements;
	
	public RideIterator(Ride[] rides) {
		this.rides = rides;
		currElement = 0;
		numElements = rides.length;
	}
	public boolean hasNext() {
		return currElement < numElements;
	}
	public Ride nextRide() {
		return rides[currElement++];
	}

}