import type { JWTPayload } from 'jose';
import { decodeJwt } from 'jose';

/**
 * 不验证密钥直接解析凭证
 */
export const decodeToken = (token: string): JWTPayload => {
    return decodeJwt(token);
};
