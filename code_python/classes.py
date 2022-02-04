import pygame
from pygame.locals import*
import os


class Joueur:
    def __init__(self, x, y, textureDroite, textureGauche, gravite, touches, nom):
        self.x = x
        self.y = y
        self.hitbox = textureDroite.get_rect()
        self.textureCourante = textureDroite
        self.textureDroite = textureDroite
        self.textureGauche = textureGauche
        self.gravite = gravite
        self.touches = touches
        self.nom = nom

    def render(self, surface):

        surface.blit(self.textureCourante, (self.rect.x, self.rect.y))
        surface.blit(self.nom, ((self.rect.x), (self.rect.y - 25)))

    def update(self, events, liste_key):

        if liste_key[self.touches["gauche"]]:
            self.x -= 3
            self.rect.x = self.x
            self.textureCourante = self.textureGauche

        if liste_key[self.touches["droite"]]:
            self.x += 3
            self.rect.x = self.x
            self.textureCourante = self.textureDroite

        if liste_key[self.touches["saut"]] and self.y == 613:
            self.vy -= 20
            self.rect.y = self.y

        if liste_key[self.touches['pouvoir']]:
            return 1

        self.y += self.vy
        self.rect.y = self.y
        self.vy += self.gravite

        if self.y >= 613:
            self.vy = 0

        self.x = min(960, self.x)
        self.x = max(0, self.x)
        self.y = min(613, self.y)


class Ballon:
    def __init__(self, x, y, texture, speed):
        self.texture = texture
        self.speed = speed
        self.rect = texture.get_rect()
        self.rect.x = x
        self.rect.y = y
        self.collided = [0, 0]

    def render(self, surface):
        surface.blit(self.texture, (self.rect.x, self.rect.y))

    def mouvement(self, collidelisteJoueur, collidelistePannier, panierGauche, panierDroit):
        self.rect = self.rect.move(self.speed)

        if self.rect.left < 0 or self.rect.right > 1000:
            self.speed[0] = -self.speed[0]
        if self.rect.top < 201 or self.rect.bottom > 700:
            self.speed[1] = - self.speed[1]

        i = self.rect.collidelist(collidelistePannier)
        j = self.rect.collidelist(collidelisteJoueur)

        if j != -1:
            if self.collided[j] == 0:
                cRectJ = collidelisteJoueur[i]

                if cRectJ.top < self.rect.bottom:
                    self.speed[1] = -self.speed[1]

                if cRectJ.right > self.rect.left or cRectJ.left > self.rect.right:
                    if self.speed[0] == 0:
                        self.speed[0] = 1
                    self.speed[0] = -self.speed[0]
                self.collided[j] = 1

        else:
            self.collided = [0, 0]
        if i != -1:
            cRectP = collidelistePannier[i]
            if cRectP.top < self.rect.bottom:
                self.speed[1] = -self.speed[1]

            if cRectP.right > self.rect.left or cRectP.left > self.rect.right:
                self.speed[0] = -self.speed[0]

        if panierGauche.colliderect(self.rect):
            if panierGauche.top == self.rect.top and self.speed[1] == 2:
                return 1

        if panierDroit.colliderect(self.rect):
            if panierDroit.top == self.rect.top and self.speed[1] == 2:
                return 2


class pouvoir:
    def __init__(self, effet, au_joueur, au_ballon, joueur, ballon, coordonées):
        self.effet = effet
        self.au_joueur = au_joueur
        self.au_ballon = au_ballon
        self.joueur = joueur
        self.ballon = ballon
        self.coordonées = coordonées

    def application_pouvoir(self, quel_pouvoir):
        self.quel_pouvoir = quel_pouvoir

        if self.au_joueur == 1:
            self.joueur.touches = self.effet

        if self.au_ballon == 1:
            if self.quel_pouvoir == 2:
                self.ballon.rect.x, self.ballon.rect.y = self.coordonées
                print(self.ballon.rect.x,self.ballon.rect.y)
                self.ballon.speed = self.effet

            if self.quel_pouvoir == 3:
                self.ballon.speed = self.effet
