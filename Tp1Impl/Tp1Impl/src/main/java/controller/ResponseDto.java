package controller;

public class ResponseDto {
	
	String stringResp;
	double numResp;
	
	public String getStringResp() {
		return stringResp;
	}
	
	
	public double getNumResp() {
		return numResp;
	}

	public ResponseDto(String stringResp, double numResp) {
		super();
		this.stringResp = stringResp;
		this.numResp = numResp;
	}


}
