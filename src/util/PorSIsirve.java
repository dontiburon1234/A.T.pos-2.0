package util;

public class PorSIsirve {
	
	/*
	 * 
		else if(e.getKeyChar()==KeyEvent.VK_0) {
			txtCampoGained.setText(modFactura.dato("", txtCampoGained.getText(), tipoCampo));
		}else if(e.getKeyChar()==KeyEvent.VK_1) {
			txtCampoGained.setText(modFactura.dato("", txtCampoGained.getText(), tipoCampo));
		}else if(e.getKeyChar()==KeyEvent.VK_2) {

		}else if(e.getKeyChar()==KeyEvent.VK_3) {

		}else if(e.getKeyChar()==KeyEvent.VK_4) {

		}else if(e.getKeyChar()==KeyEvent.VK_5) {

		}else if(e.getKeyChar()==KeyEvent.VK_6) {

		}else if(e.getKeyChar()==KeyEvent.VK_7) {

		}else if(e.getKeyChar()==KeyEvent.VK_8) {

		}else if(e.getKeyChar()==KeyEvent.VK_9) {

		}else if(e.getKeyChar()==KeyEvent.VK_COLON) {  //.

		}else if(e.getKeyChar()==KeyEvent.VK_ASTERISK) { //*

		}else if(e.getKeyChar()==KeyEvent.VK_MINUS) {//-

		}
	 * */
	

	/*try {
		if (txtCampoGained.getName() == txtCampoLost.getName()) {
			System.out.println("ConFactura.focusGained() 2 SON IGUALES");
		}


		if(e.getComponent().getName().equals("DocumentoCliente")) {
			String nombreCliente = "";
			if (txtCampoGained.getText().equals("")) {
				nombreCliente = maestroDB.consultarDocumentoCliente("1");
			} else {
				// TODO hay que buscar si le corresponde una lista de precios VIP id_lista_precio_cliente
				String numeroDocumentoCliente = txtCampoGained.getText().replace(",","");
				nombreCliente = maestroDB.consultarDocumentoCliente(numeroDocumentoCliente);
				if (nombreCliente.equals("bad")) {
					JOptionPane.showMessageDialog(null, "Documento equivocado del cliente.");
					//intCapturaArticulo.getTxtDocumentoCliente().requestFocus();
				} else {
					intCapturaArticulo.getTxtNombreCliente().setText(nombreCliente);
				}
			}
		}
		else if (txtCampoGained == intCapturaArticulo.getTxtCodigoVendedor()) {
			String nombreVendedor = "";
			if (!txtCampoGained.getText().equals("")) {
				int idVendedor = Integer.parseInt(txtCampoGained.getText());
				nombreVendedor = maestroDB.consultarNombreVendedor(idVendedor);
			}
			if (nombreVendedor.equals("bad")) {
				intCapturaArticulo.getTxtCodigoVendedor().setText("1");
					intCapturaArticulo.getTxtNombreVendedor().setText("GENERICO");
				JOptionPane.showMessageDialog(null, "C\u00F3digo equivocado del vendedor.");
				intCapturaArticulo.getTxtCodigoVendedor().requestFocus();
			} else {
				intCapturaArticulo.getTxtNombreVendedor().setText(nombreVendedor);
			}

		}*/

	/*else if (txtCampoGained == intCapturaArticulo.getTxtCodigoArticulo()) {
			System.out.println("ConFactura.focusGained() Salió de CodigoArticulo y su valor es " + txtCampoGained.getText());
			// con solo colocar el código de artículo alimenta la tabla de compras
			// lee la tabla parámetros campo capturaCantidadFactura, true captura la cantidad, false captura solo código.
			manejaSoloCodigoArticulo = Boolean.parseBoolean(maestroDB.cargarUnParametro("capturaCantidadFactura"));
			articuloFactura = new ArticuloFactura();
			articuloFactura = maestroDB.buscarArticulo(intCapturaArticulo.getTxtCodigoArticulo().getText());
			if(articuloFactura.getId_articulo()==0){
				JFrame frame =new JFrame();
				JOptionPane.showMessageDialog(frame,"Artículo no existe.","ATposMovil - Advertencia",JOptionPane.WARNING_MESSAGE);
			}else{
				if(!manejaSoloCodigoArticulo){
					int vendedor = Integer.parseInt(intCapturaArticulo.getTxtCodigoVendedor().getText());
					String codigoArticulo = intCapturaArticulo.getTxtCodigoArticulo().getText();
					String nombreArticuloLargo = articuloFactura.getNombreArticuloLargo();
					int unidad = 0;
					double cantidad;
					if(intCapturaArticulo.getTxtCantidad().getText().equals("")){
						cantidad = 1;
					}else{
						cantidad = Double.parseDouble(intCapturaArticulo.getTxtCantidad().getText());
					}
					double valorUnitario = articuloFactura.getValorUnitario();
					if(articuloFactura.getValorUnitario()==0.0){
						if(intCapturaArticulo.getTxtValorUnitario().getText().equals("")){
							intCapturaArticulo.getTxtValorUnitario().requestFocus();
						}else{
							valorUnitario = Double.parseDouble(intCapturaArticulo.getTxtValorUnitario().getText());
						}
					}else{
						double descuento = 0.0;
						double valorTotal = cantidad*valorUnitario;
						if(vendedor>0 && !codigoArticulo.equals("") && !nombreArticuloLargo.equals("") && cantidad>0.0 && valorUnitario>0.0){
							TablaArticulos tablaArticulos = new TablaArticulos(vendedor, codigoArticulo, nombreArticuloLargo, unidad, cantidad, valorUnitario, descuento,valorTotal);
							tablaFacturaModelo.adicionaArticulo(tablaArticulos);
						}else{
							if(vendedor == 0)intCapturaArticulo.getTxtCodigoVendedor().requestFocus();
							if(codigoArticulo.equals(""))intCapturaArticulo.getTxtCodigoArticulo().requestFocus(); 
							if(cantidad==0.0)intCapturaArticulo.getTxtCantidad().requestFocus();
							if(valorUnitario==0.0)intCapturaArticulo.getTxtValorUnitario().requestFocus();
						}
						dTotalFactura = dTotalFactura + valorTotal;
						intCapturaArticulo.getTxtTotalFactura().setText(""+dTotalFactura);

						intCapturaArticulo.getTxtCodigoArticulo().setText("");
						intCapturaArticulo.getTxtNombreArticulo().setText("");
						intCapturaArticulo.getTxtCantidad().setText("");
						intCapturaArticulo.getTxtUnidades().setText("");
						intCapturaArticulo.getTxtMedida().setText("");
						intCapturaArticulo.getTxtValorUnitario().setText("");
						intCapturaArticulo.getTxtValorTotal().setText("");
						intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
					}
				}else{
					intCapturaArticulo.getTxtNombreArticulo().setText(articuloFactura.getNombreArticuloLargo());
					intCapturaArticulo.getTxtMedida().setText(articuloFactura.getNombreUnidadMedida());
					intCapturaArticulo.getTxtValorUnitario().setText(""+articuloFactura.getValorUnitario());
					if(intCapturaArticulo.getTxtCantidad().getText().equals("")){
						intCapturaArticulo.getTxtCantidad().setText("1");
					}
					intCapturaArticulo.getTxtCantidad().requestFocus();
				}					
			}

		} else if (txtCampoGained == intCapturaArticulo.getTxtUnidades()) {
			System.out.println("ConFactura.focusGained() Salió de Unidades y su valor es " + txtCampoGained.getText());

		} else if (txtCampoGained == intCapturaArticulo.getTxtCantidad()) {
			System.out.println("ConFactura.focusGained() Salió de Cantidad y su valor es " + txtCampoGained.getText()+" getTxtCodigoArticulo "+intCapturaArticulo.getTxtCodigoArticulo().getText());
			//				if(intCapturaArticulo.getTxtCodigoArticulo().getText().equals("")){
			//					intCapturaArticulo.getTxtCodigoArticulo().requestFocus();
			//					System.out.println("ConFactura.focusGained() getTxtCantidad dentro de if ");
			//				}

		} else if (txtCampoGained == intCapturaArticulo.getTxtValorUnitario()) {
			System.out.println("ConFactura.focusGained() Salió de Valorunitario y su valor es " + txtCampoGained.getText() );

		} 

	} catch (Exception e1) {
		System.out.println("ConFactura.focusGained() ERROR ");
		e1.printStackTrace();
	}*/

}
