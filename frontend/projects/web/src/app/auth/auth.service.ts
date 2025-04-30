import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface User {
    id: string;
    username: string;
    email: string;
    roles: string[];
}

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private currentUserSubject = new BehaviorSubject<User | null>(null);
    public currentUser$ = this.currentUserSubject.asObservable();

    private _isAuthenticated = new BehaviorSubject<boolean>(false);
    public isAuthenticated$ = this._isAuthenticated.asObservable();

    constructor() {
        this.checkAuthStatus();
    }

    /**
     * Initialize Keycloak and check authentication status
     */
    private async checkAuthStatus(): Promise<void> {
        try {
            // Check if user is already authenticated
            const response = await fetch('/api/auth/me', {
                credentials: 'include',
            });

            if (response.ok) {
                const user = await response.json();
                this.currentUserSubject.next(user);
                this._isAuthenticated.next(true);
            } else {
                this.currentUserSubject.next(null);
                this._isAuthenticated.next(false);
            }
        } catch (error) {
            console.error('Error checking auth status:', error);
            this.currentUserSubject.next(null);
            this._isAuthenticated.next(false);
        }
    }

    /**
     * Login user via Keycloak
     */
    login(): void {
        // Redirect to Keycloak login
        window.location.href =
            'http://localhost:8081/realms/quarkus/protocol/openid-connect/auth?' +
            'client_id=backend-service&' +
            'redirect_uri=' +
            encodeURIComponent(window.location.origin) +
            '&' +
            'response_type=code&' +
            'scope=openid profile email';
    }

    /**
     * Logout user
     */
    logout(): void {
        // Clear local state
        this.currentUserSubject.next(null);
        this._isAuthenticated.next(false);

        // Redirect to Keycloak logout
        window.location.href =
            'http://localhost:8081/realms/quarkus/protocol/openid-connect/logout?' +
            'client_id=backend-service&' +
            'post_logout_redirect_uri=' +
            encodeURIComponent(window.location.origin);
    }

    /**
     * Get current user
     */
    getCurrentUser(): User | null {
        return this.currentUserSubject.value;
    }

    /**
     * Check if user is authenticated
     */
    isAuthenticated(): boolean {
        return this._isAuthenticated.value;
    }

    /**
     * Check if user has specific role
     */
    hasRole(role: string): boolean {
        const user = this.getCurrentUser();
        return user ? user.roles.includes(role) : false;
    }

    /**
     * Check if user is admin
     */
    isAdmin(): boolean {
        return this.hasRole('admin');
    }
}
