import CryptoJS from 'crypto-js';
import { trim } from 'radash';

const KEY = '1234567812345678';
const IV = '1234567812345678';
const CryptoJS_KEY = CryptoJS.enc.Utf8.parse(KEY);
const CryptoJS_IV = CryptoJS.enc.Utf8.parse(IV);
const CryptoJS_Cipher_Option = {
    iv: CryptoJS_IV,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.Pkcs7,
};

/**
 * 加密
 * @param text
 */
const aesEncrypt = (text: string) => {
    const encrypted = CryptoJS.AES.encrypt(trim(text), CryptoJS_KEY, CryptoJS_Cipher_Option);
    return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
};

/**
 * 加密
 * @param text
 */
export const encrypt = (text: string) => {
    const encrypted = aesEncrypt(text);
    return CryptoJS.enc.Hex.stringify(CryptoJS.enc.Utf8.parse(encrypted));
};
