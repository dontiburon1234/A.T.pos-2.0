package clases;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosImprimirFactura {

	@SerializedName("EncabezadoFactura")
	@Expose
	private EncabezadoFactura encabezadoFactura;
	@SerializedName("PreFactura")
	@Expose
	private PreFactura preFactura;
	@SerializedName("Domicilio")
	@Expose
	private Domicilio domicilio;
	@SerializedName("ItemPreFactura")
	@Expose
	private List<ItemPreFactura> itemPreFactura = null;
	@SerializedName("MedioPagoPreFactura")
	@Expose
	private List<MedioPagoPreFactura> medioPagoPreFactura = null;
	@SerializedName("IvaValor")
	@Expose
	private List<IvaValor> ivaValor = null;

	public EncabezadoFactura getEncabezadoFactura() {
		return encabezadoFactura;
	}

	public void setEncabezadoFactura(EncabezadoFactura encabezadoFactura) {
		this.encabezadoFactura = encabezadoFactura;
	}
	
	public PreFactura getPreFactura() {
		return preFactura;
	}

	public void setPreFactura(PreFactura preFactura) {
		this.preFactura = preFactura;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public List<ItemPreFactura> getItemPreFactura() {
		return itemPreFactura;
	}

	public void setItemPreFactura(List<ItemPreFactura> itemPreFactura) {
		this.itemPreFactura = itemPreFactura;
	}

	public List<MedioPagoPreFactura> getMedioPagoPreFactura() {
		return medioPagoPreFactura;
	}

	public void setMedioPagoPreFactura(List<MedioPagoPreFactura> medioPagoPreFactura) {
		this.medioPagoPreFactura = medioPagoPreFactura;
	}

	public List<IvaValor> getIvaValor() {
		return ivaValor;
	}

	public void setIvaValor(List<IvaValor> ivaValor) {
		this.ivaValor = ivaValor;
	}

}
