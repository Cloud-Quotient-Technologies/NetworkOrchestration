package entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BgpRequestBody {
    @XmlElement String IpNetw;
    @XmlElement Integer NumInst;

}
