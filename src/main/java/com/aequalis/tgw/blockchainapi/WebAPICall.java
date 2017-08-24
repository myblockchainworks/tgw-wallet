/**
 * 
 */
package com.aequalis.tgw.blockchainapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.json.JSONObject;

import com.aequalis.tgw.dto.UserDTO;

/**
 * @author anand
 *
 */
public class WebAPICall {
	
	public static String BLOCKCHAIN_BASE = "";
	public static String BLOCKCHAIN_URL = "";
	
	public static String icoAddress = "";
	
	public static void setProperties() {
		if (BLOCKCHAIN_BASE.equals("")) {
			Properties properties = new Properties();
			try {
				properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("tgw.properties"));
				BLOCKCHAIN_BASE = properties.getProperty("BLOCKCHAIN_BASE");
				BLOCKCHAIN_URL = properties.getProperty("BLOCKCHAIN_URL");
				icoAddress = properties.getProperty("icoAddress");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String registerNewUser(String passcode) {
		String bcAddress = null;
		try {
			URL url = new URL(BLOCKCHAIN_URL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.REGISTER_DATA.replace("PARAM1", passcode);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("result"))
					bcAddress = json.getString("result");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bcAddress;
	}
	
	public static Boolean unlockUser(String bcAddress, String passcode) {
		Boolean result = false;
		try {
			URL url = new URL(BLOCKCHAIN_URL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.UNLOCK_DATA.replace("PARAM1", bcAddress).replace("PARAM2", passcode);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("result"))
					result = json.getBoolean("result");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String sendTransaction(String fromAddres, String toAddress, String amount) {
		String result = null;
		try {
			URL url = new URL(BLOCKCHAIN_URL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.SEND_TRANSACTION.replace("PARAM1", fromAddres).replace("PARAM2", toAddress).replace("PARAM3", amount);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("result"))
					result = json.getString("result");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getBalance(String myAddress) {
		String result = null;
		try {
			URL url = new URL(BLOCKCHAIN_URL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.GET_BALANCE.replace("PARAM1", myAddress);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("result"))
					result = json.getString("result");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static UserDTO createAccount(String passcode) {
		UserDTO user = null;
		try {
			URL url = new URL(BLOCKCHAIN_BASE + WEBAPI.CREATEACCOUNT);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.CREATEACCOUNT_DATA.replace("PARAM1", passcode);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("Address")) {
					user = new UserDTO();
					user.setAddress(json.getString("Address"));
					user.setKeystore(json.getJSONObject("Keystore").toString());
					user.setPrivateKey(json.getString("PrivateKey"));
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static String myTokenBalance(String address) {
		String balance = null;
		try {
			URL url = new URL(BLOCKCHAIN_BASE + WEBAPI.MYTOKENBALANCE);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.MYTOKENBALANCE_DATA.replace("PARAM1", address);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("balance")) {
					balance = json.getString("balance");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	public static String accessAccountUsingPrivateKey(String privateKey) {
		String address = null;
		try {
			URL url = new URL(BLOCKCHAIN_BASE + WEBAPI.ACCESSACCOUNTUSINGPRIVATEKEY);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.ACCESSACCOUNTUSINGPRIVATEKEY_DATA.replace("PARAM1", privateKey);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("Address")) {
					address = json.getString("Address");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return address;
	}
	
	public static String transferToken(String fromAddress, String privateKey, String toAddress, String amount) {
		String response = null;
		try {
			URL url = new URL(BLOCKCHAIN_BASE + WEBAPI.TRANSFERTOKEN);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.TRANSFERTOKEN_DATA.replace("PARAM1", fromAddress).replace("PARAM2", privateKey).replace("PARAM3", toAddress).replace("PARAM4", amount);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("status")) {
					response = json.getString("hash");
				}
			} else {
				br = new BufferedReader(new InputStreamReader((httpCon.getErrorStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				response = sb.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static String buyToken(String fromAddress, String privateKey, String amount) {
		String response = null;
		try {
			URL url = new URL(BLOCKCHAIN_BASE + WEBAPI.BUYTOKEN);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.BUYTOKEN_DATA.replace("PARAM1", fromAddress).replace("PARAM2", privateKey).replace("PARAM3", amount);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("status")) {
					response = json.getString("hash");
				}
			} else {
				br = new BufferedReader(new InputStreamReader((httpCon.getErrorStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				response = sb.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
