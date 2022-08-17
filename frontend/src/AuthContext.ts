import { createContext } from 'react';
import { TokenData } from 'util/token';

export type AuthcontextData = {
    authenticated: boolean;
    tokenData?: TokenData;
    userId?: number;
};

export type AuthContextType = {
    authContextData: AuthcontextData,
    setAuthContextData: (authcontextData: AuthcontextData) => void;
};
export const AuthContext = createContext<AuthContextType>({
    authContextData: {
        authenticated: false
    },
    setAuthContextData: () => null
});