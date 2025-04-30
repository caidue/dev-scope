import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService, User } from '../auth/auth.service';

@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [CommonModule],
    template: `
        <div class="dashboard-container">
            <header class="dashboard-header">
                <h1>Dashboard</h1>
                <div class="user-info" *ngIf="currentUser">
                    <span>Welcome, {{ currentUser.username }}!</span>
                    <button (click)="logout()" class="logout-button">Logout</button>
                </div>
            </header>

            <div class="dashboard-content" *ngIf="currentUser">
                <div class="user-card">
                    <h2>User Information</h2>
                    <div class="user-details">
                        <p><strong>Username:</strong> {{ currentUser.username }}</p>
                        <p><strong>Email:</strong> {{ currentUser.email }}</p>
                        <p><strong>Roles:</strong> {{ currentUser.roles.join(', ') }}</p>
                    </div>
                </div>

                <div class="features-card">
                    <h2>SSO Features</h2>
                    <div class="feature-list">
                        <div class="feature-item" *ngIf="authService.isAdmin()">
                            <span class="feature-icon">[Admin]</span>
                            <span>Admin Access - You have administrative privileges</span>
                        </div>
                        <div class="feature-item">
                            <span class="feature-icon">[SSO]</span>
                            <span>Single Sign-On - You're authenticated via Keycloak</span>
                        </div>
                        <div class="feature-item">
                            <span class="feature-icon">[Session]</span>
                            <span>Session Management - Your session is managed centrally</span>
                        </div>
                        <div class="feature-item">
                            <span class="feature-icon">[Roles]</span>
                            <span>Role-Based Access - Access controlled by your roles</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `,
    styles: [
        `
            .dashboard-container {
                min-height: 100vh;
                background-color: #f8f9fa;
            }

            .dashboard-header {
                background: white;
                padding: 1rem 2rem;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .dashboard-header h1 {
                margin: 0;
                color: #333;
            }

            .user-info {
                display: flex;
                align-items: center;
                gap: 1rem;
            }

            .logout-button {
                background: #dc3545;
                color: white;
                border: none;
                padding: 8px 16px;
                border-radius: 4px;
                cursor: pointer;
            }

            .logout-button:hover {
                background: #c82333;
            }

            .dashboard-content {
                padding: 2rem;
                max-width: 1200px;
                margin: 0 auto;
            }

            .user-card,
            .features-card {
                background: white;
                padding: 2rem;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                margin-bottom: 2rem;
            }

            .user-card h2,
            .features-card h2 {
                margin-top: 0;
                color: #333;
                border-bottom: 2px solid #007bff;
                padding-bottom: 0.5rem;
            }

            .user-details p {
                margin: 0.5rem 0;
                color: #666;
            }

            .feature-list {
                display: grid;
                gap: 1rem;
            }

            .feature-item {
                display: flex;
                align-items: center;
                gap: 1rem;
                padding: 1rem;
                background: #f8f9fa;
                border-radius: 4px;
            }

            .feature-icon {
                font-size: 1.5rem;
            }
        `,
    ],
})
export class DashboardComponent implements OnInit {
    currentUser: User | null = null;

    constructor(public authService: AuthService) {}

    ngOnInit(): void {
        this.authService.currentUser$.subscribe(user => {
            this.currentUser = user;
        });
    }

    logout(): void {
        this.authService.logout();
    }
}
