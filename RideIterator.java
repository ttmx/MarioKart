class RideIterator {
	
	private Ride[] rides;
	private int currElement;
	private int numElements;
	
	public RideIterator(Ride[] rides, int numElements) {
		this.rides = rides;
		this.numElements = numElements;
		currElement = 0;
		
	}
	public boolean hasNext() {
		return currElement < numElements;
	}
	public Ride nextRide() {
		return rides[currElement++];
	}

}