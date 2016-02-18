package bertrand.test;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.BIP38PrivateKey;
import org.bitcoinj.crypto.BIP38PrivateKey.BadPassphraseException;
import org.bitcoinj.crypto.LinuxSecureRandom;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;

import sun.awt.image.BytePackedRaster;

public class AddressTest {
	
	private static final MainNetParams MAINNET = MainNetParams.get();
    private static final TestNet3Params TESTNET = TestNet3Params.get();

//	public static void main(String[] args) throws BadPassphraseException {
//		BIP38PrivateKey encryptedKey = BIP38PrivateKey.fromBase58(MAINNET,
//                "6PRVWUbkzzsbcVac2qwfssoUJAN1Xhrg6bNk8J7Nzm5H7kxEbn2Nh2ZoGg");
//                 5HueCGU8rMjxEXxiPuD5BDku4MkFqeZyd4dZ1jvhTVqvbTLvyTJ
//        ECKey key = encryptedKey.decrypt("TestingOneTwoThree");
//        System.out.println(key.getPrivateKeyAsWiF(MAINNET));
//	}

	public static void main(String[] args) throws BadPassphraseException {
		BIP38PrivateKey encryptedKey = BIP38PrivateKey.fromBase58(MAINNET,
                 "5J3mBbAH58CpQ3Y5RNJpUKPE62SQ5tfcvU2JpbnkeyhfsYB1Jcn");
        ECKey key = encryptedKey.decrypt("TestingOneTwoThree");
        System.out.println(key.getPrivateKeyAsWiF(MAINNET));
	}
	
	public static void test() {
		String privateKey = getPrivateKey();
		privateKey = "80" + privateKey;
		String oncePrivateKey = Utils.HEX.encode(Sha256Hash.hashTwice(Utils.HEX.decode(privateKey.toLowerCase()))).toUpperCase();
		System.out.println(oncePrivateKey);
		privateKey = privateKey + oncePrivateKey.substring(0,8);
		System.out.println(privateKey);
		String address = Base58.encode(Utils.HEX.decode(privateKey.toLowerCase()));
		System.out.println(address);
	}
	
	public static String getPrivateKey() {
		LinuxSecureRandom random = new LinuxSecureRandom();
		byte[] privateKeyBytes = random.engineGenerateSeed(32);
		return Utils.HEX.encode(privateKeyBytes).toUpperCase();
	}
	

}
