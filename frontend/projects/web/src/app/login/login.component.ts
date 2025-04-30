import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth/auth.service';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [CommonModule],
    template: `
        <div class="login-container">
            <div class="login-card">
                <h2>Welcome</h2>
                <p>Please sign in to continue</p>
                <button (click)="login()" class="login-button">Sign In with Keycloak</button>
            </div>
        </div>
    `,
    styles: [
        `
            .login-container {
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                background-color: #f5f5f5;
            }

            .login-card {
                background: white;
                padding: 2rem;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                text-align: center;
                max-width: 400px;
                width: 100%;
            }

            h2 {
                font-size: 2rem;
                color: #333;
                margin: 0 0 1rem 0;
            }

            p {
                color: #666;
                margin-bottom: 2rem;
            }

            .login-button {
                background: #007bff;
                color: white;
                border: none;
                padding: 12px 24px;
                border-radius: 4px;
                font-size: 1rem;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .login-button:hover {
                background: #0056b3;
            }
        `,
    ],
})
export class LoginComponent {
    constructor(private authService: AuthService) {}

    login(): void {
        this.authService.login();
    }
}
