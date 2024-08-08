package com.globits.da;

public class Constants {
	public static final int CODE_LENGTH_MIN = 6;
	public static final int CODE_LENGTH_MAX = 10;
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}";
	public static final String PHONE_REGEX = "^[0-9]{1,11}$";
	public static final String DATE_FORMAT ="dd-MM-yyyy";
	public static final int AGE_MIN = 0;
	public static final int CERTIFICATE_QUANTITY_MAX =3;
	public static final String DELETE_SUCCESSFUL = "Deleted successfully";
	public static final String EXCEL_FILE_PATH = "excelFilePath1.xlsx";
	public static final String EXPORT_EXCEL_SUCCESSFUL = "Export excel file successfully.";
	public static final String IMPORT_EXCEL_SUCCESSFUL = "Import excel file successfully.";
	public static enum StaffType {
		Sale(1), // nhân viên bán hàng
		Cashier(2), // nhân viên thu ngân
		Other(3)// khác
		;

		private Integer value;

		private StaffType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public static enum ChannelAds {// kenh quang cao
		Webiste(1), // website
		Contextual_Advertiser(2), // khen hquang cao
		Social_Netword(3), // mang xa hoi
		Youtube_channel(4)// youtube
		;

		private Integer value;

		private ChannelAds(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}
	
	public static enum Social_Netword {// kenh quang cao
		Facebook(1), // website
		Zalo(2), // khen hquang cao
		Tiktok(3), // mang xa hoi
		Other(4)// youtube
		;

		private Integer value;

		private Social_Netword(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

}
