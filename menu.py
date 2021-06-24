import pygame
from pygame.locals import*
import main
import fonctions
import os
import webbrowser

pygame.init()


boucle_principale = 1

screen = pygame.display.set_mode((700, 500))
menu = fonctions.load_image("menu.png")
Play = fonctions.load_image("Play.png")
Help = fonctions.load_image("Help.png")
son_menu = pygame.mixer.Sound(os.path.join("musiques", "music_menu.wav"))
screen.blit(menu, (0, 0))

fontButton = pygame.font.SysFont("Arial", 24)
buttonPlay = fontButton.render('PLAY !',1,(0,0,0))
buttonHelp = fontButton.render('HELP !',1,(0,0,0))

print(buttonPlay.get_width()/2)
print(buttonHelp.get_width())

screen.blit(buttonPlay,(350 - buttonPlay.get_width()/2, 295))
screen.blit(buttonHelp,(350 - buttonHelp.get_width()/2, 395))



while boucle_principale:

    #son_menu.play()
    pygame.display.flip()
    boucle_accueil = 1
    boucle_jeu = 1

    while boucle_accueil:
        events = pygame.event.get()

        for event in events:
            if event.type == QUIT or event.type == KEYDOWN and event.key == K_ESCAPE:
                boucle_principale = 0
                boucle_jeu = 0
                boucle_accueil = 0

            if event.type == MOUSEMOTION:
                if (320 < event.pos[0] < 391) and (283 < event.pos[1] < 325):
                    pygame.mouse.set_cursor(*pygame.cursors.diamond)
                    buttonPlay = fontButton.render('PLAY !',1,(0,0,255))
                    screen.blit(buttonPlay,(350 - buttonPlay.get_width()/2, 295))    
                elif (322 < event.pos[0] < 393) and (389 < event.pos[1] < 431):
                    pygame.mouse.set_cursor(*pygame.cursors.diamond)
                    buttonHelp = fontButton.render('HELP !',1,(0,0,255))
                    screen.blit(buttonHelp,(350 - buttonHelp.get_width()/2, 395))
                else:
                    pygame.mouse.set_cursor(*pygame.cursors.arrow)
                    screen.blit(menu, (0, 0))
                    buttonPlay = fontButton.render('PLAY !',1,(0,0,0))
                    buttonHelp = fontButton.render('HELP !',1,(0,0,0))
                    screen.blit(buttonPlay,(350 - buttonPlay.get_width()/2, 295))
                    screen.blit(buttonHelp,(350 - buttonHelp.get_width()/2, 395))
            if event.type == MOUSEBUTTONUP:
                if (320 < event.pos[0] < 391) and (283 < event.pos[1] < 325):
                    son_menu.stop()
                    boucle_accueil = 0
                elif (322 < event.pos[0] < 393) and (389 < event.pos[1] < 431):
                    webbrowser.open(os.path.join("html", "ggggg.html"))
        pygame.display.flip()

    while boucle_jeu:
        main.start()
        boucle_jeu = 0 
