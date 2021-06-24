import pygame
import fonction
import os

pygame.init()

screen = pygame.display.set_mode((700, 500))
img = fonction.load_image("Play.png")


screen.blit(img , (0, 0))
pygame.display.flip()