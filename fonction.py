import pygame
from pygame.locals import*
import os


pygame.init()
pygame.font.init()

def load_image():
    
    screen_chargement = pygame.display.set_mode((500, 500))
    fond_chargement = pygame.image.load(os.path.join("images","chargement.png")).convert_alpha()

    persoDroite1 = pygame.image.load(os.path.join("images","perso1d.png")).convert_alpha()
    persoDroite2 = pygame.image.load(os.path.join("images","perso2d.png")).convert_alpha()
    persoGauche1 = pygame.image.load(os.path.join("images","perso1g.png")).convert_alpha()
    persoGauche2 = pygame.image.load(os.path.join("images","perso2g.png")).convert_alpha()

    barrePouvoirJ1 = pygame.image.load(os.path.join("images","barrePouvoirsJ1.png")).convert_alpha()
    barrePouvoirJ1_vide = pygame.image.load(os.path.join("images","barrePouvoirsJ1_vide.png")).convert_alpha()
    barrePouvoirJ2 = pygame.image.load(os.path.join("images","barrePouvoirsJ2.png")).convert_alpha()
    barrePouvoirJ2_vide = pygame.image.load(os.path.join("images","barrePouvoirsJ2_vide.png")).convert_alpha()
    barreScore = pygame.image.load(os.path.join("images","barreScore.png")).convert_alpha() 

    image_tp_ball = pygame.image.load(os.path.join("images","Tp_ball.png")).convert_alpha()
    image_inverse_commande = pygame.image.load(os.path.join("images","inverse_commande.png")).convert_alpha()
    image_freeze_ball = pygame.image.load(os.path.join("images","freeze_ball.png")).convert_alpha()
    image_freeze_joueur = pygame.image.load(os.path.join("images","freeze_joueur.jpg")).convert_alpha()

    fond = pygame.image.load(os.path.join("images","terrain_basket.png")).convert_alpha()

    ballonBasket = pygame.image.load(os.path.join("images","ballon_de_basket.png")).convert_alpha()

    podium = pygame.image.load(os.path.join("images","podium.png")).convert_alpha()
    podium_egalite = pygame.image.load(os.path.join("images","podium_egalite.png")).convert_alpha()

    ok = pygame.image.load(os.path.join("images","ok.png")).convert_alpha()

    return fond_chargement,persoDroite1,persoDroite2,ok, screen_chargement,persoGauche1,persoGauche2,ballonBasket,podium,podium_egalite,fond,barrePouvoirJ1,barrePouvoirJ2,barreScore,image_tp_ball,image_inverse_commande,image_freeze_ball,barrePouvoirJ1_vide,barrePouvoirJ2_vide,image_freeze_joueur

def load_image_menu():

    screen = pygame.display.set_mode((700, 500))
    menu = pygame.image.load(os.path.join("images","menu.png")).convert_alpha()
    Play = pygame.image.load(os.path.join("images","Play.png")).convert_alpha()
    Help = pygame.image.load(os.path.join("images","Help.png")).convert_alpha()

    return screen, menu, Play, Help


def chargement_perso(fond_chargement,persoDroite1,persoDroite2,screen_chargement,nom, x, y):
    
    
    screen_chargement.blit(fond_chargement, (0, 0))
    screen_chargement.blit(persoDroite1, (40, 120))
    screen_chargement.blit(persoDroite2, (40, 340))

    fontChargement = pygame.font.SysFont("Arial", 22)
    boucle_name = 1
    name = ''
    while boucle_name:
        events = pygame.event.get()

        for event in events:
            if event.type == KEYDOWN and event.key != K_RETURN and event.key != K_BACKSPACE:
                name += event.unicode
                nom = fontChargement.render(name, 1, (0, 0, 0))
                screen_chargement.blit(nom, (x, y))
            if event.type == KEYDOWN and event.key == K_BACKSPACE:
                a = len(name)
                name = name[0:a-1]
                screen_chargement.blit(fond_chargement, (0, 0))
                screen_chargement.blit(persoDroite1, (40, 120))
                screen_chargement.blit(persoDroite2, (40, 340))
                nom = fontChargement.render(name, 1, (0, 0, 0))
                screen_chargement.blit(nom, (x, y))
            if event.type == KEYDOWN and event.key == K_RETURN:
                boucle_name = 0
                return nom
        pygame.display.flip()


def chargement(fond_chargement,persoDroite1,persoDroite2,ok,screen_chargement,nomJ1, nomJ2):
    chargement = 1

    screen_chargement = pygame.display.set_mode((500, 500))

    screen_chargement.blit(fond_chargement, (0, 0))
    screen_chargement.blit(persoDroite1, (40, 120))
    screen_chargement.blit(persoDroite2, (40, 340))

    pygame.display.flip()

    #music_chargement = pygame.mixer.Sound(os.path.join("musiques","music_chargement.wav"))
    #music_chargement.play()

    fontChargement = pygame.font.SysFont("Arial", 22)

    t = fontChargement.render('', 1, (255, 255, 255))
    r = fontChargement.render('', 1, (255, 255, 255))

    while chargement:
        events = pygame.event.get()

        for event in events:
            if event.type == QUIT or (event.type == KEYDOWN and event.key == K_ESCAPE):
                chargement = 0

            if event.type == MOUSEMOTION:
                if (125 < event.pos[0]) and (58 < event.pos[1] < 80):
                    texte = fontChargement.render(">>", 1, (0, 0, 0))
                    screen_chargement.blit(texte, (125, 58))
                elif (125 < event.pos[0]) and (288 < event.pos[1] < 310):
                    texte = fontChargement.render(">>", 1, (0, 0, 0))
                    screen_chargement.blit(texte, (125, 288))
                elif (369 < event.pos[0] < 411) and (416 < event.pos[1] < 455):
                    screen_chargement.blit(ok, (369, 416))
                else:
                    screen_chargement.blit(fond_chargement, (0, 0))
                    screen_chargement.blit(persoDroite1, (40, 120))
                    screen_chargement.blit(persoDroite2, (40, 340))

            if event.type == MOUSEBUTTONUP:
                if ((125 < event.pos[0]) and (58 < event.pos[1] < 80)):
                    t = chargement_perso(fond_chargement,persoDroite1,persoDroite2,screen_chargement,nomJ1, 135, 58)
                    screen_chargement.blit(t, (125, 58))
                if ((125 < event.pos[0]) and (288 < event.pos[1] < 310)):
                    r = chargement_perso(fond_chargement,persoDroite1,persoDroite2,screen_chargement,nomJ2, 135, 288)
                    screen_chargement.blit(r, (125, 288))
                if (369 < event.pos[0] < 411) and (416 < event.pos[1] < 455):
                    #music_chargement.stop()
                    return t, r
                    chargement = 0
        screen_chargement.blit(t, (125, 58))
        screen_chargement.blit(r, (125, 288))
        pygame.display.flip()


def podium(scoreJ1, scoreJ2,screen,podium,podium_egalite,persoDroite1,persoDroite2):
    affichageScoreJ1 = str(scoreJ1)
    affichageScoreJ2 = str(scoreJ2)

   

    myFont = pygame.font.SysFont("Arial", 72)

    egalite = myFont.render('égalité', 1, (255, 255, 255))

    score_displayJ1 = myFont.render(affichageScoreJ1, 1, (255, 255, 255))
    score_displayJ2 = myFont.render(affichageScoreJ2, 1, (255, 255, 255))

    screen.blit(score_displayJ1, (428, 100))
    screen.blit(score_displayJ2, (572, 100))


    if scoreJ1 > scoreJ2:
        screen.blit(podium, (0, 0))
        screen.blit(persoDroite1, (425, 178))
        screen.blit(persoDroite2, (586, 250))
        screen.blit(score_displayJ1, (428, 10))
        screen.blit(score_displayJ2, (572, 10))


    elif scoreJ2 > scoreJ1:
        screen.blit(podium, (0, 0))
        screen.blit(persoDroite2, (425, 178))
        screen.blit(persoDroite1, (586, 250))
        screen.blit(score_displayJ1, (572, 10))
        screen.blit(score_displayJ2, (428, 10))

    else:
        screen.blit(podium_egalite, (0, 0))
        screen.blit(persoDroite1, (420, 180))
        screen.blit(persoDroite2, (520, 180))
        screen.blit(egalite,(400,50))
        

    pygame.display.flip()

    continuer_finGame = 1

    while continuer_finGame:
        events = pygame.event.get()

        for event in events:
            if event.type == QUIT:
                continuer_finGame = 0

def image_quel_pouvoir(quel_pouvoir,quel_joueur,surface,image_tp_ball,image_inverse_commande,image_freeze_ball,image_freeze_joueur):
        if quel_joueur == 1:
            if quel_pouvoir == 1:
                surface.blit(image_inverse_commande,(10,10))
            if quel_pouvoir == 2 :
                surface.blit(image_tp_ball,(10,10))
            if quel_pouvoir == 3 :
                surface.blit(image_freeze_ball,(10,10))
            if quel_pouvoir == 4 :
                surface.blit(image_freeze_joueur,(10,10))
            
        
        if quel_joueur == 2:
            if quel_pouvoir == 1:
                surface.blit(image_inverse_commande,(890,10))
            if quel_pouvoir == 2 :
                surface.blit(image_tp_ball,(890,10))
            if quel_pouvoir == 3 :
                surface.blit(image_freeze_ball,(890,10))
            if quel_pouvoir == 4 :
                surface.blit(image_freeze_ball,(890,10))

        
        


