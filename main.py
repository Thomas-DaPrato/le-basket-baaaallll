from pygame import display
import classes
from math import ceil, floor
import pygame
from pygame.locals import*
import fonctions
import os
from random import randint


pygame.init()
pygame.font.init()


def start ():

    infoScreen = pygame.display.Info()
    #screenGame = pygame.display.set_mode((1000,700))
    screenGame = pygame.display.set_mode((infoScreen.current_w,infoScreen.current_h),pygame.FULLSCREEN)

    #chargement du fond
    fond = fonctions.load_image("terrain_basket.png")
    ratioFond = fond.get_width() / fond.get_height()
    fond = pygame.transform.scale(fond,(infoScreen.current_w, int(infoScreen.current_w / ratioFond)))

    #chargement de la barre de pouvoir
    barrePouvoirJ1 = fonctions.load_image("barrePouvoir.png")
    barrePouvoirJ2 = pygame.transform.rotate(barrePouvoirJ1,180)

    #chargement des joueurs
    J1TextureDroite = fonctions.load_image("perso1d.png") 
    J1TextureGauche = fonctions.load_image("perso1g.png")
    J2TextureDroite = fonctions.load_image("perso2d.png")
    J2TextureGauche = fonctions.load_image("perso2g.png")

    middleHauteurBandeauScoreAndTime = (infoScreen.current_h - fond.get_height()) / 2
    middleLargeurBandeauScoreAndTime = infoScreen.current_w / 2

    screenGame.blit(fond, (0, infoScreen.current_h - fond.get_height()))
    screenGame.blit(barrePouvoirJ1, (infoScreen.current_w * 1/4 , middleHauteurBandeauScoreAndTime - barrePouvoirJ1.get_height() / 2))
    screenGame.blit(barrePouvoirJ2, (infoScreen.current_w * 3/4 - barrePouvoirJ2.get_width() , middleHauteurBandeauScoreAndTime - barrePouvoirJ1.get_height() / 2))


    minute = 4
    seconde = 59

    fontTimeAndScore = pygame.font.SysFont("Arial", int(middleHauteurBandeauScoreAndTime))

    #score a rendre en variable
    score_displayJ1 = fontTimeAndScore.render("0", 1, (255, 255, 255))
    score_displayJ2 = fontTimeAndScore.render("0", 1, (255, 255, 255))

    #timer a rendre en variable
    timerMinute_display = fontTimeAndScore.render(
        "4", 1, (255, 255, 255))
    timerSeconde_display = fontTimeAndScore.render(
        "59", 1, (255, 255, 255))

    deuxPoints = fontTimeAndScore.render(":", 1, (255, 255, 255))

    displayTimer = pygame.Surface((60 + timerMinute_display.get_width() + deuxPoints.get_width() + timerSeconde_display.get_width(), timerMinute_display.get_height()))

    displayTimer.blit(timerMinute_display, (0,0))
    displayTimer.blit(deuxPoints, (timerMinute_display.get_width() + 30,0))
    displayTimer.blit(timerSeconde_display, (timerMinute_display.get_width() + deuxPoints.get_width()+ 60,0))

    screenGame.blit(score_displayJ1, (infoScreen.current_w * 1/4 + barrePouvoirJ1.get_width() + 10, middleHauteurBandeauScoreAndTime - score_displayJ1.get_height()/2))
    screenGame.blit(score_displayJ2, (infoScreen.current_w * 3/4 - barrePouvoirJ2.get_width() - 30, middleHauteurBandeauScoreAndTime - score_displayJ2.get_height()/2))
    screenGame.blit(displayTimer, (middleLargeurBandeauScoreAndTime - displayTimer.get_width() / 2 , middleHauteurBandeauScoreAndTime - displayTimer.get_height()/2))
    
    screenGame.blit(J1TextureDroite ,(0,0))
    screenGame.blit(J2TextureGauche ,(0,0))

    boucle_jeu = 1

    while boucle_jeu:
        events = pygame.event.get()

        for event in events:
            if event.type == QUIT or event.type == KEYDOWN and event.key == K_ESCAPE:
                boucle_jeu = 0

        pygame.display.flip()

start()