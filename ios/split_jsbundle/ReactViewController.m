//
//  ReactViewController.m
//  statusbar
//
//  Created by ZhuXueliang on 2020/3/22.
//  Copyright © 2020 Facebook. All rights reserved.
//
#import <React/RCTBridge.h>
#import <React/RCTRootView.h>
#import "ReactViewController.h"
#import "RNJSLoader.h"

@interface ReactViewController ()
@property (nonatomic, strong) NSURL* url;
@property (nonatomic, strong) NSString* name;
@end

@implementation ReactViewController

- (instancetype)initWithModuleName:(NSString *)name url:(NSURL *)url {
  if (self = [super init]) {
    self.url = url;
    self.name = name;
  }
  return self;
}

- (void)viewDidLoad {
  [super viewDidLoad];
  self.view.backgroundColor = [UIColor whiteColor];
  if ([[RNJSLoader sharedInstance] loadJSModule:self.name path:self.url]) {
    [self initView];
  }
}

- (void)initView {
  RCTBridge *bridge = [RNJSLoader sharedInstance].bridge;
  RCTRootView* view = [[RCTRootView alloc] initWithBridge:bridge moduleName:self.name initialProperties:nil];
  view.frame = self.view.bounds;
  view.backgroundColor = [UIColor whiteColor];
  [self setView:view];
}

@end
