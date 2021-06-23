import classes
from math import ceil, floor
import pygame
from pygame.locals import*
import fonction
import os
from random import randint

pygame.init()
pygame.font.init()


def start():
    #chargement des ressources
    fond_chargement,persoDroite1,persoDroite2,ok, screen_chargement,persoGauche1,persoGauche2,ballonBasket,podium,podium_egalite,fond,barrePouvoirJ1,barrePouvoirJ2,barreScore,image_tp_ball,image_inverse_commande,image_freeze_ball,barrePouvoirJ1_vide,barrePouvoirJ2_vide,image_freeze_joueur = fonction.load_image()
    nomJ1 = ''
    nomJ2 = ''
    nomJ1, nomJ2 = fonction.chargement(fond_chargement,persoDroite1,persoDroite2,ok,screen_chargement,nomJ1, nomJ2)

    screen = pygame.display.set_mode((1000, 700))
    #screen = pygame.display.set_mode((1000,700),pygame.FULLSCREEN)
    #son_jeu = pygame.mixer.Sound(os.path.join("musiques","music_jeu.wav"))
    #son_jeu.play()
    
    screen.blit(fond, (0, 200))
    screen.blit(barrePouvoirJ1,(0,0))
    screen.blit(barrePouvoirJ2,(659,0))
    screen.blit(barreScore,(380,0))
    
    #definition des objets
    ##definition de la balle
    speed = [0, 2]
    
    ball = classes.Ballon(470, 420, ballonBasket, speed)
    
    speed_freeze_ball = [0,0]

    gravite = 3

    ##definition des joueurs
    dict1 = {"saut": K_SPACE, "droite": K_d, "gauche": K_a,'pouvoir':K_LSHIFT}
    dict2 = {"saut": K_UP, "droite": K_RIGHT, "gauche": K_LEFT,'pouvoir':K_KP0 }
    
    inverse_dict1 = {"saut": K_SPACE, "droite":K_a , "gauche": K_d,'pouvoir':K_LSHIFT }
    inverse_dict2 = {"saut": K_UP, "droite":K_LEFT , "gauche":K_RIGHT ,'pouvoir':K_KP0}

    rectJ1 = pygame.Rect((50, 613), (52, 87 ))
    rectJ2 = pygame.Rect((900, 613), (52, 87))

    j1 = classes.Joueur(50, 642, persoDroite1, persoGauche1,gravite, dict1, nomJ1, rectJ1)
    j2 = classes.Joueur(900, 642, persoDroite2, persoGauche2,gravite, dict2, nomJ2, rectJ2)

    ##definition des paniers
    bordPanierGauche = pygame.Rect((89, 432), (11, 5))
    bordPanierDroit = pygame.Rect((900, 432), (11, 5))

    panierGauche = pygame.Rect((20, 432), (69, 5))
    panierDroit = pygame.Rect((912, 432), (69, 5))

    pygame.display.flip()
    pygame.key.set_repeat(10, 12)
    continuer = 1
    
    ##definition des scores
    scoreJ1 = 0
    scoreJ2 = 0

    affichageScoreJ1 = str(scoreJ1)
    affichageScoreJ2 = str(scoreJ2)

    myFont = pygame.font.SysFont("Arial", 72)

    #definition des barres de pouvoir
    black = (0,0,0)
    orange = (255,127,39)
    x_rectPouvoirJ1 = 243
    largueur_rectPouvoirJ1 = 135
    rectPouvoirJ1 = pygame.Rect((x_rectPouvoirJ1,100),(largueur_rectPouvoirJ1,30))

    largueur_rectPouvoirJ2 = 135
    rectPouvoirJ2 = pygame.Rect((662,100),(largueur_rectPouvoirJ2,30))

    screen.blit(barrePouvoirJ1_vide,(0,0))
    screen.blit(barrePouvoirJ2_vide,(659,0))
    
    ##chrono_debut
    fontChronoDebut = pygame.font.SysFont("Arial", 140)

    chronoDebut = 3

    for i in range(3):
        affichageChronoDebut = str(chronoDebut)
        chronoDebut_display = fontChronoDebut.render(affichageChronoDebut, 1, (0, 0, 0))
        screen.blit(chronoDebut_display, (470, 400))
        pygame.display.flip()
        pygame.time.delay(1000)
        screen.blit(fond, (0, 200))
        pygame.display.flip()
        chronoDebut -= 1

    chronoDebut = 'GO !'
    chronoDebut_display = fontChronoDebut.render(chronoDebut, 1, (0, 0, 0))
    screen.blit(chronoDebut_display, (400, 200))
    pygame.display.flip()
    pygame.time.delay(1000)

    #variable
    
    donner_pouvoir = 0

    pouvoir_timerJ1 = 10
    use_pouvoirJ1 = 0
    no_powerJ1 = 0
    no_powerJ1_timer = 5
    freeze_joueurJ1 = 0
   
        
        

    pouvoir_timerJ2 = 10
    use_pouvoirJ2 = 0
    no_powerJ2 = 0
    no_powerJ2_timer = 5
    freeze_joueurJ2 = 0
    
    minute = 4
    seconde = 59
    
    fullscreen = 1
    tmps_frame = 8
    #boucle de jeu
    while continuer:

        #chrono du jeu
        clock = pygame.time.Clock()
        if donner_pouvoir == 1000:
            donner_pouvoir = 0 
            
            if seconde == 0:
                seconde = 59
                minute -= 1
            seconde -= 1
            #affichage de la barre de pouvoir J1
            if use_pouvoirJ1 == 0 and no_powerJ1 == 0:
                pouvoir_timerJ1 -= 1
                x_rectPouvoirJ1 += 15
                largueur_rectPouvoirJ1 -= 15
                screen.blit(barrePouvoirJ1,(0,0))
                rectPouvoirJ1 = pygame.Rect((x_rectPouvoirJ1,100),(largueur_rectPouvoirJ1,30))
                pygame.draw.rect(screen,black,(rectPouvoirJ1))

            ##affichage de la barre de pouvoir J2
            if use_pouvoirJ2 == 0 and no_powerJ2 == 0:
                pouvoir_timerJ2 -= 1
                largueur_rectPouvoirJ2 -= 15
                screen.blit(barrePouvoirJ2,(659,0))
                rectPouvoirJ2 = pygame.Rect((662,100),(largueur_rectPouvoirJ2,30))
                pygame.draw.rect(screen,black,(rectPouvoirJ2))

            ##réinitialisation des effets au joueurs  
        
            if no_powerJ1 == 1:
                no_powerJ1_timer -= 1
                screen.blit(barrePouvoirJ1_vide,(0,0))

                if no_powerJ1_timer == 0:
                    j1 = classes.Joueur(j1.rect.x,j1.rect.y, persoDroite1, persoGauche1,gravite, dict1, nomJ1, rectJ1)
                    ball = classes.Ballon(ball.rect.x,ball.rect.y,ballonBasket,[0,2])
                    pouvoir_timerJ1 = 10
                    use_pouvoirJ1 = 0
                    no_powerJ1 = 0
                    no_powerJ1_timer = 5
                    freeze_joueurJ2 = 0
                    

            if no_powerJ2 == 1:
                no_powerJ2_timer -= 1
                screen.blit(barrePouvoirJ2_vide,(659,0))
                
                if no_powerJ2_timer == 0:
                    j2 = classes.Joueur(j2.rect.x,j2.rect.y, persoDroite2, persoGauche2,gravite, dict2, nomJ2, rectJ2)
                    ball = classes.Ballon(ball.rect.x,ball.rect.y,ballonBasket,[0,2])
                    pouvoir_timerJ2 = 10
                    use_pouvoirJ2 = 0
                    no_powerJ2 = 0
                    no_powerJ2_timer = 5
                    freeze_joueurJ1 = 0

        affichageTimerMinute = str(minute)
        affichageTimerSeconde = str(seconde)
        screen.blit(barreScore,(380,0))

        if minute < 0:
            continuer = 0


        #definition des pouvoirs
        ##pouvoir inversement des commandes
        inverse_commandJ1 = classes.pouvoir(inverse_dict1,1,0,j1,ball,0)
        inverse_commandJ2 = classes.pouvoir(inverse_dict2,1,0,j2,ball,0)
        ##pouvoir tp ball
        tp_ballJ1 = classes.pouvoir([0,2],0,1,0,ball,(932,349))
        tp_ballJ2 = classes.pouvoir([0,2],0,1,0,ball,(42,349))
        ##pouvoir freeze ball
        freeze_ballJ1 = classes.pouvoir(speed_freeze_ball,0,1,0,ball,(ball.rect.x,ball.rect.y))
        freeze_ballJ2 = classes.pouvoir(speed_freeze_ball,0,1,0,ball,(ball.rect.x,ball.rect.y))
        
        


        if pouvoir_timerJ1 == 0 and use_pouvoirJ1 == 0:
            pygame.draw.rect(screen,orange,[(228,100),(150,30)],1)
            use_pouvoirJ1 = 1
            quel_pouvoirJ1 = 2
            fonction.image_quel_pouvoir(quel_pouvoirJ1,1,screen,image_tp_ball,image_inverse_commande,image_freeze_ball,image_freeze_joueur)
            x_rectPouvoirJ1 = 243
            largueur_rectPouvoirJ1 = 135
            

        if pouvoir_timerJ2 == 0 and use_pouvoirJ2 == 0:
            pygame.draw.rect(screen,orange,[(662,100),(150,30)],1)
            use_pouvoirJ2 = 1
            quel_pouvoirJ2 = randint(1,4)
            fonction.image_quel_pouvoir(quel_pouvoirJ2,2,screen,image_tp_ball,image_inverse_commande,image_freeze_ball,image_freeze_joueur)
            largueur_rectPouvoirJ2 = 135
            

        
        #logique du jeu
        events = pygame.event.get()

        for event in events:
            if event.type == QUIT or (event.type == KEYDOWN and event.key == K_ESCAPE):
                continuer = 0
            if event.type == KEYDOWN:
                if event.key == 292:
                    if fullscreen == 0:
                        fullscreen = 1 
                        screen = pygame.display.set_mode((1000,700),pygame.FULLSCREEN)
                    else:
                        fullscreen = 0
                        screen = pygame.display.set_mode((1000,700))

                    
        liste_key = pygame.key.get_pressed()
        screen.blit(fond, (0, 200))

        

        if freeze_joueurJ1 != 1:
            pouvoirJ1 = j1.update(events, liste_key)
        if freeze_joueurJ2 != 1:
            pouvoirJ2 = j2.update(events, liste_key)
        
        
    

        ##pouvoir J1
        if pouvoirJ1 == 1 and use_pouvoirJ1 == 1:
            no_powerJ1 = 1
            screen.blit(barrePouvoirJ1_vide,(0,0))
            if quel_pouvoirJ1 == 1:
                inverse_commandJ2.application_pouvoir(quel_pouvoirJ1)
            if quel_pouvoirJ1 == 2:
                tp_ballJ1.application_pouvoir(quel_pouvoirJ1)
            if quel_pouvoirJ1 == 3:
                freeze_ballJ1.application_pouvoir(quel_pouvoirJ1)
            if quel_pouvoirJ1 == 4:
                freeze_joueurJ2 = 1


        ##pouvoir J2
        if pouvoirJ2 == 1 and use_pouvoirJ2 == 1:
            no_powerJ2 = 1
            screen.blit(barrePouvoirJ2_vide,(659,0))
            
            if quel_pouvoirJ2 == 1:
                inverse_commandJ1.application_pouvoir(quel_pouvoirJ2)
            if quel_pouvoirJ2 == 2:
                tp_ballJ2.application_pouvoir(quel_pouvoirJ2)
            if quel_pouvoirJ2 == 3:
                freeze_ballJ2.application_pouvoir(quel_pouvoirJ2)
            if quel_pouvoirJ2 == 4:
                freeze_joueurJ1 = 1


           
        #logique du jeu
        j1.render(screen)
        j2.render(screen)
        score = ball.mouvement((j1.rect, j2.rect), (bordPanierDroit, bordPanierGauche), panierGauche, panierDroit)
        ball.render(screen)
        if score == 1:
            scoreJ2 += 1
            affichageScoreJ2 = str(scoreJ2)
        if score == 2:
            scoreJ1 += 1
            affichageScoreJ1 = str(scoreJ1)
        
        ##rénitialisation des joueurs et des pouvoirs
        if score == 1 or score == 2:
            j1.rect.x, j1.rect.y= (50,600)
            j2.rect.x, j2.rect.y =(900,600)
            
            ball.rect.x,ball.rect.y = 470,420
            
            ball.speed = [0,2]

            j1 = classes.Joueur(50, 600, persoDroite1, persoGauche1,gravite, dict1, nomJ1, rectJ1)
            x_rectPouvoirJ1 = 228
            largueur_rectPouvoirJ1 = 150
            screen.blit(barrePouvoirJ1,(0,0))
            pouvoir_timerJ1 = 10
            use_pouvoirJ1 = 0
            no_powerJ1 = 0

            j2 = classes.Joueur(900, 600, persoDroite2, persoGauche2,gravite, dict2, nomJ2, rectJ2)
            largueur_rectPouvoirJ2 = 150
            screen.blit(barrePouvoirJ2,(659,0))
            pouvoir_timerJ2 = 10
            use_pouvoirJ2 = 0
            no_powerJ2 = 0
           

        #affichage du chrono
        score_displayJ1 = myFont.render(affichageScoreJ1, 1, (255, 255, 255))
        score_displayJ2 = myFont.render(affichageScoreJ2, 1, (255, 255, 255))

        timerMinute_display = myFont.render(
            affichageTimerMinute, 1, (255, 255, 255))
        timerSeconde_display = myFont.render(
            affichageTimerSeconde, 1, (255, 255, 255))

        x = myFont.render(":", 1, (255, 255, 255))

        screen.blit(score_displayJ1, (428, 100))
        screen.blit(score_displayJ2, (572, 100))
        screen.blit(timerMinute_display, (428, 30))
        screen.blit(x, (470, 30))
        screen.blit(timerSeconde_display, (500, 30))
        
        

        fps = clock.tick()

        pygame.time.wait(tmps_frame - fps)
        
        donner_pouvoir += tmps_frame

        

       

        pygame.display.flip()
    #son_jeu.stop()
    screen_podium = pygame.display.set_mode((1000,500))
    fonction.podium(scoreJ1,scoreJ2,screen_podium,podium,podium_egalite,persoDroite1,persoDroite2)
start()
