/** Java Packages. **/
package com.cooksys.assessment;

/** Java UTIL Imports. **/
import java.util.ArrayList;
import java.util.List;

/** Java XML Imports. **/
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Parts {
	List<String> name;
	
	public List<String> getName() {
		if (name == null) {
			name = new ArrayList<String>();
		}
		return name;
	}
	@XmlElement
	public void setName(List<String> name) {
		this.name = name;
	}
}
