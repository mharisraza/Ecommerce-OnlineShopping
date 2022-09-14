package com.ecommerce.entities;

public class Message {
	
		
	      private String content;
	      private String type;
	      private String cssClass;
	      private String buttonClose;
	      private String buttonDismiss;
	      
	      
	      
		public Message(String content, String type, String cssClass) {
			super();
			this.content = content;
			this.type = type;
			this.cssClass = cssClass;
		}
		
		


		public Message(String content, String type, String cssClass, String buttonClose, String buttonDismiss) {
			super();
			this.content = content;
			this.type = type;
			this.cssClass = cssClass;
			this.buttonClose = buttonClose;
			this.buttonDismiss = buttonDismiss;
		}




		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}


		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public String getCssClass() {
			return cssClass;
		}


		public void setCssClass(String cssClass) {
			this.cssClass = cssClass;
		}




		public String getButtonClose() {
			return buttonClose;
		}




		public void setButtonClose(String buttonClose) {
			this.buttonClose = buttonClose;
		}




		public String getButtonDismiss() {
			return buttonDismiss;
		}




		public void setButtonDismiss(String buttonDismiss) {
			this.buttonDismiss = buttonDismiss;
		}
		
		
		
	      
	      
	

}
