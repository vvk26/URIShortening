package com.uri.urishortening;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UriShortenService {

	@Autowired
	UriShorteningRepo uriShortenRepo;
	
	public String getShortenURI(String longUri) throws Exception{
		
		if("".equalsIgnoreCase(longUri)) {
			return "Blank Uri";
		}
		/*
		 * List<URIShortenEntity> listUri =
		 * uriShortenRepo.checkUri(objectToByteArray(longUri)); if(listUri !=null &&
		 * listUri.size() > 0) { URIShortenEntity uriEntity = listUri.get(0);
		 * if(uriEntity.getLonguri().equals(longUri)) {
		 * 
		 * return
		 * String.valueOf(Base62.idToShortURL(Integer.parseInt(String.valueOf(uriEntity.
		 * getUri_id())))); } }
		 */
		
		URIShortenEntity uriEntity = new URIShortenEntity();
		uriEntity.setLonguri(objectToByteArray(longUri));
		
		URIShortenEntity proxyEntity = uriShortenRepo.save(uriEntity);
		
		
		return String.valueOf(Base62.idToShortURL(Integer.parseInt(String.valueOf(proxyEntity.getUri_id()))));
	}
	
	public String getLongUri(String shotUri) throws Exception {
		int uriId = Base62.shortURLtoID(shotUri);
		
		URIShortenEntity userEntity = uriShortenRepo.getLongURI(uriId).get(0);
		
		return byteToObject(userEntity.getLonguri()).toString();
		
	}
	
	public byte[] objectToByteArray(Object object) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		byte[] byteValue = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(object);
			out.flush();
			byteValue = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
				out.close();
			} catch (IOException ex) {

			}
		}
		return byteValue;
	}

	public Object byteToObject(byte[] byteValue) {
		ByteArrayInputStream bis = new ByteArrayInputStream(byteValue);
		ObjectInput in = null;
		Object o = null;
		try {
			in = new ObjectInputStream(bis);
			o = in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return o;
	}
}
