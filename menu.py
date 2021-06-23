import pygame
from pygame.locals import*
import main
import fonction
import os
import webbrowser

pygame.init()


continuer = 1

while continuer:

    screen, menu, Play, Help = fonction.load_image_menu()
    son_menu = pygame.mixer.Sound(os.path.join("musiques", "music_menu.wav"))
    screen.blit(menu, (0, 0))
    son_menu.play()

    pygame.display.flip()
    continuer_accueil = 1
    continuer_jeu = 1

    while continuer_accueil:
        events = pygame.event.get()

        for event in events:
            if event.type == QUIT or event.type == KEYDOWN and event.key == K_ESCAPE:
                continuer = 0
                continuer_jeu = 0
                continuer_accueil = 0

            if event.type == MOUSEMOTION:
                if (320 < event.pos[0] < 391) and (283 < event.pos[1] < 325):
                    screen.blit(Play, (320, 283))
                elif (322 < event.pos[0] < 393) and (389 < event.pos[1] < 431):
                    screen.blit(Help, (322, 389))
                else:
                    screen.blit(menu, (0, 0))
            if event.type == MOUSEBUTTONUP:
                if (320 < event.pos[0] < 391) and (283 < event.pos[1] < 325):
                    son_menu.stop()
                    continuer_accueil = 0
                elif (322 < event.pos[0] < 393) and (389 < event.pos[1] < 431):
                    webbrowser.open(os.path.join("html", "ggggg.html"))
        pygame.display.flip()

    while continuer_jeu:
        main.start()
        continuer_jeu = 0
